package com.wyj.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wyj.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasePagedResponse<T> {

  private static final long serialVersionUID = 6484351358417308864L;

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

  private long totalCount;
  private int pageSize;
  private int pageNumber;
}
