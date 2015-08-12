package com.tw.beach.entity;

public class Account {
    private Integer balance = 0;

    public Integer balance() {
        return balance;
    }

    public Integer add(Integer amount) {
        return balance+=amount;
    }
}
