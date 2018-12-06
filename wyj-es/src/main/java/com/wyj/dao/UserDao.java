package com.wyj.dao;

import com.wyj.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface UserDao extends CrudRepository<UserEntity, String> {

}
