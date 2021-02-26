package com.david.dao;

import com.david.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();
    List<Account> findAccountByUid(Integer uid);
}
