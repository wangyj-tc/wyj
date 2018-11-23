package com.wyj.error;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.wyj.constant.CommonConstants;
import com.wyj.core.BaseResponse;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * wyj
 */
@RestController
public class JsonErrorHandler implements ErrorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonErrorHandler.class);
  private static final String PATH = "/error";

  @RequestMapping(value = PATH)
  public BaseResponse error(HttpServletRequest request, HttpServletResponse response) {

    LOGGER.error("HTTP exception: {}", response.getStatus());

    BaseResponse baseResponse = new BaseResponse();
    switch (response.getStatus()) {
      case SC_NOT_FOUND:
        baseResponse.setErrorMessage(NOT_FOUND.getReasonPhrase());
        baseResponse.setErrorMessage(NOT_FOUND.name());
        break;
      case SC_FORBIDDEN:
        baseResponse.setErrorMessage(FORBIDDEN.getReasonPhrase());
        baseResponse.setErrorCode(FORBIDDEN.name());
        break;
      case SC_UNAUTHORIZED:
        baseResponse.setErrorCode(UNAUTHORIZED.name());
        baseResponse.setErrorMessage(UNAUTHORIZED.getReasonPhrase());
        break;
      case SC_METHOD_NOT_ALLOWED:
        baseResponse.setErrorMessage(METHOD_NOT_ALLOWED.getReasonPhrase());
        baseResponse.setErrorCode(METHOD_NOT_ALLOWED.name());
        break;
      case SC_INTERNAL_SERVER_ERROR:
      default:
        baseResponse.setErrorCode(INTERNAL_SERVER_ERROR.name());
        baseResponse.setErrorCode(INTERNAL_SERVER_ERROR.getReasonPhrase());
        break;
    }
    baseResponse.setTimestamp(System.currentTimeMillis());
    baseResponse.setRequestId(
        StringUtils.isNotBlank(MDC.get(CommonConstants.REQUEST_ID_KEY)) ? MDC
            .get(CommonConstants.REQUEST_ID_KEY) : UUID.randomUUID().toString()
    );
    return baseResponse;
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }
}
