package com.david.dao;

import com.david.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(Integer userId);
    User findById(Integer userId);
    List<User> findByName(String name);
    int findTotal();
}
