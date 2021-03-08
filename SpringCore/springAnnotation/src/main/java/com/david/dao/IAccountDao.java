package com.david.dao;

import com.david.domain.Account;

import java.sql.SQLException;
import java.util.List;

/*
* 账户的持久层接口
* */
public interface IAccountDao {
    void saveAccount(Account account) throws SQLException;

    List<Account> findAllAccount() throws SQLException;

    Account findAccountByID(Integer id) throws SQLException;

    void updateAccount(Account account) throws SQLException;

    void deleteAccount(Integer id) throws SQLException;
}
