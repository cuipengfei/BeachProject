package com.second.job.tw;

import java.util.LinkedList;

/**
 * Created by ppyao on 8/17/15.
 */
public class BankManager {
    private String message;
    private LinkedList<Customer> PrminumCustomerList = new LinkedList<Customer>();

    public LinkedList<Customer> getPrminumCustomerList() {
        return PrminumCustomerList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
