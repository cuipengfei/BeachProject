package com.second.job.tw;

import java.util.Date;

/**
 * Created by ppyao on 8/12/15.
 */
public class Account extends Customer{
    private double balance;
    public Account(String name,Date dateofBirth,double balance)
    {
        super(name,dateofBirth);
        this.balance=balance;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    public double getBalance()
    {
        return balance;
    }

}
