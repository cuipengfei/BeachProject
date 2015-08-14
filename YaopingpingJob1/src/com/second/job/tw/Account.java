package com.second.job.tw;

import java.util.Date;

/**
 * Created by ppyao on 8/12/15.
 */
public class Account {
    private double balance = 0.0;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public double addBalance(double money) {
        return balance += money;
    }

    public double minusBalance(double money) {
        return balance -= money;
    }

}
