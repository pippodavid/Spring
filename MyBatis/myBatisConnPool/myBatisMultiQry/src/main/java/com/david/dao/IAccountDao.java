package com.david.dao;

import com.david.Account;
import com.david.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();

    //查询所有账户，并且带有用户的名称地址信息
    List<AccountUser> findAllAccountWithUserInfo();
}
