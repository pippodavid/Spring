package com.david.dao.impl;

import com.david.dao.IAccountDao;
import com.david.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
* 账户的持久层接口实现
* */
@Repository(value = "accountDao")
public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void saveAccount(Account account) throws SQLException{
        runner.update("insert into account(name, salary) values (?, ?)", account.getName(), account.getMoney());
        System.out.println("Account has been saved...");
    }

    public List<Account> findAllAccount() throws SQLException{
        return runner.query("select * from account", new BeanListHandler<Account>(Account.class));
    }

    public Account findAccountByID(Integer id) throws SQLException{
        return runner.query("select * from account where id = ?", new BeanHandler<Account>(Account.class), id);
    }

    public void updateAccount(Account account) throws SQLException{
       runner.update("update account set name=?, salary=? where id=?",
                account.getName(), account.getMoney(), account.getId());
    }

    public void deleteAccount(Integer id) throws SQLException{
        runner.update("delete account where id=?", id);
    }
}
