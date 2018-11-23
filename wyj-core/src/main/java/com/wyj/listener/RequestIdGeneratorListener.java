package com.wyj.listener;

import com.wyj.constant.CommonConstants;
import java.util.UUID;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;


/**
 * 生成requestId,结束之后移除
 */
@WebListener
@Component
public class RequestIdGeneratorListener implements ServletRequestListener {

  @Override
  public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    MDC.remove(CommonConstants.REQUEST_ID_KEY);
  }

  @Override
  public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    MDC.put(CommonConstants.REQUEST_ID_KEY, UUID.randomUUID().toString());
  }
}
