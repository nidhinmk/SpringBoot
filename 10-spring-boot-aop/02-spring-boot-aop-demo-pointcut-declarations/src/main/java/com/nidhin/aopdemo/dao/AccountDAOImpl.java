package com.nidhin.aopdemo.dao;

import com.nidhin.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean vipFLag) {
        System.out.println(getClass() + " Doing DB Work: Adding an Account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + " getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
