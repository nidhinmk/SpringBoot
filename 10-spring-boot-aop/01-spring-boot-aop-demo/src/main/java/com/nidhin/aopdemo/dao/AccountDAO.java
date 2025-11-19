package com.nidhin.aopdemo.dao;

import com.nidhin.aopdemo.Account;

public interface AccountDAO {
    public void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();
}
