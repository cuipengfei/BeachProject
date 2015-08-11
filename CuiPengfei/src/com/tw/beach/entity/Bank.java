package com.tw.beach.entity;

public class Bank {
    public Boolean addCustomer(Customer customer) {
        return customer != Customer.invalidCustomer();
    }
}
