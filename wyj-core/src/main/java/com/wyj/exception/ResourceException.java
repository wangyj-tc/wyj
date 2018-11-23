package com.wyj.exception;

/**
 * @author Fuwenming
 * @created 2018/4/13
 **/
public class ResourceException extends CloudCommonException {

  public ResourceException(String message) {
    super(message);
  }

  public ResourceException(String message, Throwable t) {
    super(message, t);
  }
}
