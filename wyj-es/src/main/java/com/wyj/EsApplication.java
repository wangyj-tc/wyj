package com.wyj;

import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author yongjian.wang
 * @create 29 13:54
 **/
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.wyj.dao")
public class EsApplication {
  
  public static void main(String[] args){
    new SpringApplicationBuilder(EsApplication.class).run(args);
  }

}
