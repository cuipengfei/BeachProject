package com.second.job.tw.request;

import com.second.job.tw.Customer;

/**
 * Created by ppyao on 8/13/15.
 */
public class CustomerRequest {
    private Customer customer;
    private RequestType type;
    private double money;

    public CustomerRequest(Customer customer, RequestType type, double money) {
        this.customer = customer;
        this.type = type;
        this.money = money;
    }

    public Customer getCustomer() {
        return customer;
    }

    public RequestType getType() {
        return type;
    }

    public double getMoney() {
        return money;
    }

    public static CustomerRequest despoitRequst(Customer customer, double money) {
        return new CustomerRequest(customer, RequestType.depositMoney, money);
    }

    public static CustomerRequest withdrawRequest(Customer customer, double money) {
        return new CustomerRequest(customer, RequestType.withdrawMoney, money);
    }
}
