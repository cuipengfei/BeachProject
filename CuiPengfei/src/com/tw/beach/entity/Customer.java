package com.tw.beach.entity;

import java.util.Date;

public class Customer {
    private final String nickName;
    private final Date birthDay;

    private Customer(String nickName, Date birthDay) {
        this.nickName = nickName;
        this.birthDay = birthDay;
    }

    public static Customer createCustomer(String nickName, Date date) {
        return new Customer(nickName, date);
    }

    public String nickName() {
        return nickName;
    }
}
