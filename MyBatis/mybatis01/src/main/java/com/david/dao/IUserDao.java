package com.david.dao;

import com.david.User;

import java.util.List;

/*
* 用户的持久层接口*/
public interface IUserDao {

    List<User> findAll();
    void insertBatch(List<User> users);
}
