package com.nidhin.aopdemo.dao;

import com.nidhin.aopdemo.Account;

public interface AccountDAO {
    public void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
