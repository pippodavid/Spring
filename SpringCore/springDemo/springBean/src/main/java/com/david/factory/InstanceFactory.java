package com.david.factory;

import com.david.service.IAccountService;
import com.david.service.impl.AccountServiceImpl;

public class InstanceFactory {

    public IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
