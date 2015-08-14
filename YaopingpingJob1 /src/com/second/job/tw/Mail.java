package com.second.job.tw;

import com.second.job.tw.Customer;

/**
 * Created by ppyao on 8/14/15.
 */
public class Mail {
    private Customer customer;
    private String message;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mail(Customer customer, String message) {
        this.customer = customer;
        this.message = message;
    }


}
