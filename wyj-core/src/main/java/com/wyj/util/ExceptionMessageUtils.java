package com.wyj.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author Fuwenming
 * @created 2018/4/16
 **/
public class ExceptionMessageUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageUtils.class);
  private static Map<String, String> CODE_MESSAGE_MAPPING;
  private static final Pattern PATTERN = Pattern.compile("\\$\\{\\w*\\}");

  static {
    try {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      Properties properties = new Properties();
      //read from core properties
      try (InputStream resourceStream = loader.getResourceAsStream("error_msg_core.properties")) {
        properties.load(resourceStream);
      }
      CODE_MESSAGE_MAPPING = properties.entrySet().stream().collect(Collectors.toMap(
          e -> String.valueOf(e.getKey()),
          e -> String.valueOf(e.getValue())));

      //read from project-scope properties
      properties.clear();
      try (InputStream resourceStream = loader.getResourceAsStream("error_msg.properties")) {
        properties.load(resourceStream);
      }

      CODE_MESSAGE_MAPPING.putAll(properties.entrySet().stream().collect(Collectors.toMap(
          e -> String.valueOf(e.getKey()),
          e -> String.valueOf(e.getValue())))
      );
    } catch (Exception e) {
      LOGGER.error("fail to convert properties to map", e);
    }
  }

  public static String getMessage(String source, Map<String, ?> params) {
    return build(CODE_MESSAGE_MAPPING.getOrDefault(source, source), params);
  }

  /**
   * replace all keywords by param value
   * @param source -- message that will be replaced
   * @param params -- params will be used in replacement
   */
  private static String build(String source, Map<String, ?> params) {
    if (StringUtils.isEmpty(source)) {
      return "";
    }
    if (CollectionUtils.isEmpty(params)) {
      return source;
    }
    Matcher matcher = PATTERN.matcher(source);
    while (matcher.find()) {
      String match = matcher.group();
      String keyword = match.substring(2, match.length() - 1);
      if (params.containsKey(keyword)) {
        source = source.replace(match, String.valueOf(params.get(keyword)));
      }
    }
    return source;
  }
}
