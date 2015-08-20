package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.requests.CustomerRequest;

import java.util.Calendar;
import java.util.Date;

public class DepositHandler implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        Customer customer = customerRequest.getCustomer();
        customer.setBalance(customer.getBalance() + customerRequest.getBalance());
        giveBonus(customer);
        return customer.getBalance();
    }

    private void giveBonus(Customer customer) {
        Calendar calendar = Calendar.getInstance();
       // calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(2011,12,12);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 2);
        if (calendar.getTime().before(new Date())) {
            customer.setBalance(customer.getBalance() + 5);
        }
    }
}
