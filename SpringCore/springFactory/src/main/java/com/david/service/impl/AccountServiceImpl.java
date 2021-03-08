package com.david.service.impl;

import com.david.dao.IAccountDao;
import com.david.dao.impl.AccountDaoImpl;
import com.david.factory.BeanFactory;
import com.david.service.IAccountService;

/*
* 账户的业务层实现类
* */
public class AccountServiceImpl implements IAccountService {
    //private IAccountDao dao = new AccountDaoImpl();
    private IAccountDao dao = (IAccountDao) BeanFactory.getBean("accountDao");
    public void saveAccount() {
        dao.saveAccount();
    }
}
