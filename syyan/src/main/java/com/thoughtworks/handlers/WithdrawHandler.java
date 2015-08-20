package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.exception.OverdrawException;
import com.thoughtworks.requests.CustomerRequest;

public class WithdrawHandler implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest){

        Customer customer = customerRequest.getCustomer();
        double money = customerRequest.getBalance();

        if (customer.getBalance() < customerRequest.getBalance()) throw new OverdrawException();
        customer.setBalance(customer.getBalance() - money);

        return customer.getBalance();

    }
}
