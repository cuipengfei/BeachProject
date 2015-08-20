package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.requests.CustomerRequest;

import java.util.Calendar;

public class DepositHandler implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        Customer customer = customerRequest.getCustomer();
        customer.setBalance(customer.getBalance() + customerRequest.getBalance());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -2);
        if (!customer.isGiveBonus() && calendar.getTime().after(customer.getDateOfJoin()))
            giveBonus(customer);
        return customer.getBalance();
    }

    private void giveBonus(Customer customer) {
        customer.setGiveBonus(true);
        customer.setBalance(customer.getBalance() + 5);
    }
}
