package com;

import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author yongjian.wang
 * @create 22 18:13
 **/
@org.springframework.boot.autoconfigure.SpringBootApplication(scanBasePackages = {"com"})
public class SpringBootApplication {
  
  
  public static void main(String[] args){
    new SpringApplicationBuilder(SpringBootApplication.class).run(args);
  }

}
