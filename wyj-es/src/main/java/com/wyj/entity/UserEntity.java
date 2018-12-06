package com.wyj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author yongjian.wang
 * @create 29 13:48
 **/
@Document(indexName = "wyj", type = "user")
public class UserEntity {

  @Id
  private String id;

  private String name;

  private Integer age;

  private Integer sex;

}
