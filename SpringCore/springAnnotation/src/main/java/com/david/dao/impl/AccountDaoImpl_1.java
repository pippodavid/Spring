package com.david.dao.impl;

import com.david.dao.IAccountDao;
import com.david.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("accountDao1")
public class AccountDaoImpl_1{

    public void saveAccount() {
        System.out.println("saved account1");
    }
}
