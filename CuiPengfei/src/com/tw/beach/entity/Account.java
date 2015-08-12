package com.tw.beach.entity;

public class Account {
    private Integer balance = 0;

    public Integer balance() {
        return balance;
    }

    public void add(Integer amount) {
        balance += amount;
    }

    public void minus(Integer amount) {
        balance -= amount;
    }
}
