package com.david.service.impl;

import com.david.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 账户的业务层实现类
 */
@Service("accountService")
public class AccountServiceImpl implements IAccountService{

    @Override
    public void saveAccount() {
        System.out.println("saveAccout is executed");
        //int i=1/0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("updateAccount is executed"+i);

    }

    @Override
    public int deleteAccount() {
        System.out.println("deleteAccount is executed");
        return 0;
    }
}
