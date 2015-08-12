package com.tw.beach.entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers = new ArrayList<>();

    public Boolean addCustomer(Customer customer) {
        boolean shouldAddCustomer = shouldAdd(customer);
        if (shouldAddCustomer) {
            customers.add(customer);
        }
        return shouldAddCustomer;
    }

    private boolean shouldAdd(Customer customer) {
        boolean customerIsValid = customer != Customer.invalidCustomer();
        boolean isNotSameName = isNotSameName(customer);
        return customerIsValid && isNotSameName;
    }

    private boolean isNotSameName(final Customer newCustomer) {
        return !customers.stream()
                .anyMatch(customer ->
                        customer.nickName().equals(newCustomer.nickName()));
    }

    public void deposit(Customer customer, Integer amount) {
        customer.account().add(amount);
    }
}
