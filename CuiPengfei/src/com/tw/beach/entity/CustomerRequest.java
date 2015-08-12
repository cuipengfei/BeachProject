package com.tw.beach.entity;

import static com.tw.beach.entity.RequestType.WithDraw;

public class CustomerRequest {
    private Customer customer;
    private final RequestType type;
    private final Integer amount;

    public CustomerRequest(Customer customer, RequestType type, Integer amount) {
        this.customer = customer;
        this.type = type;
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public RequestType getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public static CustomerRequest withDraw(Customer customer, Integer amount) {
        return new CustomerRequest(customer, WithDraw, amount);
    }

    public static CustomerRequest deposit(Customer customer, Integer amount) {
        return new CustomerRequest(customer, RequestType.Deposit, amount);
    }
}
