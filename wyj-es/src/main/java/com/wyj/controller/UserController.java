package com.wyj.controller;

import com.wyj.dao.UserDao;
import com.wyj.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongjian.wang
 * @create 29 13:51
 **/
@RestController
public class UserController {

  @Autowired
  private UserDao userDao;

  @PostMapping("addUser")
  public UserEntity addUser(@RequestBody UserEntity userEntity) {
    return userDao.save(userEntity);
  }


  @PostMapping("findUser")
  public UserEntity findUser(@RequestHeader String id) {
    return userDao.findById(id).get();
  }

}
