package com.david.factory;

import com.david.service.IAccountService;
import com.david.service.impl.AccountServiceImpl;

public class StaticFactory {
    public static IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
