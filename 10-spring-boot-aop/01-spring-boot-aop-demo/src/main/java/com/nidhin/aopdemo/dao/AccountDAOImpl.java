package com.nidhin.aopdemo.dao;

import com.nidhin.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    @Override
    public void addAccount(Account account, boolean vipFLag) {
        System.out.println(getClass() + " Doing DB Work: Adding an Account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " doWork()");
        return false;
    }
}
