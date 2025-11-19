package com.nidhin.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public void addAccount() {
        System.out.println(getClass() + " Doing DB Work: Adding a Membership");
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + " I am going to Sleep now ZZZZ...");
    }
}
