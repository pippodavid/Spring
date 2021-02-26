package com.david.dao;

import com.david.QueryVo;
import com.david.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(Integer userId);
    List<User> findByName(String name);
    List<User> findByQryVo(QueryVo vo);
    List<User> findByCondition(User user);
    List<User> findByIds(QueryVo vo);
}
