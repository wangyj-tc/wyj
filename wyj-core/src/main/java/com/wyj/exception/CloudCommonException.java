package com.wyj.exception;

import com.wyj.util.ExceptionMessageUtils;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author Fuwenming
 * @created 2018/4/13
 **/
@Getter
@Setter
@ToString
public class CloudCommonException extends RuntimeException {

  private String message;
  private String code;
  private int status;
  private Map<String, ?> params;

  public CloudCommonException(Throwable t) {
    super(t);
    this.message = t.getMessage();
    this.code = HttpStatus.INTERNAL_SERVER_ERROR.name();
    this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
  }

  public CloudCommonException(String message) {
    super(message);
    this.message = message;
    this.code = HttpStatus.INTERNAL_SERVER_ERROR.name();
    this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
  }

  public CloudCommonException(HttpStatus status, String message) {
    super(message);
    this.code = status.name();
    this.status = status.value();
    this.message = message;
  }

  public CloudCommonException(HttpStatus status, String message, Map<String, ?> params) {
    this.code = status.name();
    this.status = status.value();
    this.message = ExceptionMessageUtils.getMessage(message, params);
    this.params = params;
  }


  public CloudCommonException(String message, Throwable t) {
    super(message, t);
    this.message = message;
    this.code = HttpStatus.INTERNAL_SERVER_ERROR.name();
    this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
  }

  public CloudCommonException(String code, Map<String, ?> params) {
    this.message = ExceptionMessageUtils.getMessage(code, params);
    this.code = code;
    this.params = params;
  }

  public CloudCommonException(String code, String message) {
    this.message = message;
    this.code = code;
  }

  public CloudCommonException(String code, String message, Map<String, ?> params) {
    this.message = ExceptionMessageUtils.getMessage(message, params);
    this.code = code;
    this.params = params;
  }

  public CloudCommonException(int statueCode, String errorCode, String errorMessage) {
    this.status = statueCode;
    this.code = errorCode;
    this.message = errorMessage;
  }

  public CloudCommonException(int statueCode, String errorCode, String errorMessage,
      Map<String, ?> params) {
    this.status = statueCode;
    this.code = errorCode;
    this.message = errorMessage;
    this.params = params;
  }
}
