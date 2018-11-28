package com.wyj.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @author yongjian.wang
 * @create 28 18:28
 **/
@JobHandler("myJob")
@Component
public class MyJob extends IJobHandler {

  @Override
  public ReturnT<String> execute(String s) throws Exception {
    System.out.println("Hello world");
    return null;
  }
}
