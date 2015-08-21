package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.exception.OverdrawException;
import com.thoughtworks.requests.CustomerRequest;

public class WithdrawHandler implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        Customer customer = customerRequest.getCustomer();
        double limitBalance = customer.isOverdraftAllowed() ? -1000 : 0;
        if (customer.getBalance() - customerRequest.getBalance() < limitBalance) throw new OverdrawException();
        customer.setBalance(customer.getBalance() - customerRequest.getBalance());
        return customer.getBalance();
    }
}
