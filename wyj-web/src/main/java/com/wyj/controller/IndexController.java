package com.wyj.controller;

import com.wyj.core.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongjian.wang
 * @create 23 12:02
 **/
@RestController
public class IndexController {


  @PostMapping("index")
  public BaseResponse getIndex() {
    return BaseResponse.builder().data("success").build();
  }

}
