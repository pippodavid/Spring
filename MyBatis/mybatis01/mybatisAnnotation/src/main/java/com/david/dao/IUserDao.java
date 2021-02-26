package com.david.dao;

import com.david.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
* 用户的持久层接口*/
public interface IUserDao {

    @Select("select * from user;")
    List<User> findAll();
}
