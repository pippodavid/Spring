package com.david.dao;

import com.david.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(Integer id);
}
