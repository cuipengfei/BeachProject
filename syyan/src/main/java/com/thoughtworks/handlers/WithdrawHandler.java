package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.exception.OverdrawException;
import com.thoughtworks.exception.StandardException;
import com.thoughtworks.requests.CustomerRequest;

public class WithdrawHandler implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        Customer customer = customerRequest.getCustomer();
        if (!customer.isOverdraftAllowed() && customer.getBalance() < 0) throw new StandardException();

        if (!customer.isOverdraftAllowed() && customer.getBalance() < customerRequest.getBalance()) {
            throw new OverdrawException();
        } else if (customer.getBalance() - customerRequest.getBalance() < -1000) throw new OverdrawException();

        customer.setBalance(customer.getBalance() - customerRequest.getBalance());
        return customer.getBalance();
    }
}
