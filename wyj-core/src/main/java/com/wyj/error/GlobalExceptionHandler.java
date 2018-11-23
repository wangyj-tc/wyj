package com.wyj.error;

import com.sun.media.sound.InvalidFormatException;
import com.wyj.core.BaseResponse;
import com.wyj.exception.CloudCommonException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wyj
 * @created 2018/4/16
 **/
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = CloudCommonException.class)
  @ResponseBody
  public BaseResponse cloudCommonExceptionHandler(HttpServletRequest request,
      HttpServletResponse response, CloudCommonException e) {
    response.setStatus(e.getStatus() == 0 ? response.getStatus() : e.getStatus());
    logException(request, e);
    return BaseResponse.builder().data(e.getParams()).errorCode(e.getCode())
        .errorMessage(e.getMessage()).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public BaseResponse validationError(HttpServletRequest request,
      MethodArgumentNotValidException ex) {
    logException(request, ex);
    BindingResult result = ex.getBindingResult();
    final List<FieldError> fieldErrors = result.getFieldErrors();
    String errorMsg = fieldErrors.stream().map(
        e -> "Object:" + e.getObjectName() + ", field:" + e.getField() + ", message:" + e
            .getDefaultMessage())
        .collect(Collectors.joining("|"));
    return BaseResponse.builder().errorCode("InvalidParameter").errorMessage(errorMsg)
        .build();
  }

  @ExceptionHandler(value = {HttpMessageNotReadableException.class, IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public BaseResponse validationJsonError(HttpServletRequest request,
      InvalidFormatException ex) {
    return BaseResponse.builder().errorCode("InvalidParameter").errorMessage(ex.getMessage())
        .build();
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public BaseResponse defaultExceptionHandler(HttpServletRequest request, Exception e) {
    logException(request, e);
    return BaseResponse.builder().errorMessage(e.getMessage())
        .errorCode("InternalError").build();
  }

  private String messageBuilder(HttpServletRequest request, Exception e) {
    return "host:" + request.getRemoteHost() + " request url: " + request.getMethod() + " - "
        + request.getRequestURI() + ", error info: " + e.getMessage();
  }

  private String traceBuilder(Exception e) {
    StringBuilder trace = new StringBuilder();
    if (e.getStackTrace() != null && e.getStackTrace().length > 0) {
      for (StackTraceElement ele : e.getStackTrace()) {
        trace.append("\n  at ").append(ele.toString());
      }
    }
    return trace.toString();
  }

  private void logException(HttpServletRequest request, Exception e) {
    String message = messageBuilder(request, e);
    String trace = traceBuilder(e);
    LOGGER.error("[Exception Info] {}", message);
    LOGGER.error("[Exception Trace] {}", trace);
  }
}
