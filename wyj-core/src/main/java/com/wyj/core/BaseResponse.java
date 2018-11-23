package com.wyj.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wyj.constant.CommonConstants;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;

/**
 * wyj
 * @param <T>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

  private static final long serialVersionUID = -9085596927473232923L;

  @Builder.Default
  private String requestId = MDC.get(CommonConstants.REQUEST_ID_KEY);

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String errorMessage;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String errorCode;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  @Builder.Default
  private long timestamp = System.currentTimeMillis();
}
