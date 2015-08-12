package com.tw.beach.entity;

import java.util.ArrayList;
import java.util.List;

import static com.tw.beach.entity.Handlers.findHandler;

public class Bank {
    private List<Customer> customers = new ArrayList<>();

    public Boolean addCustomer(Customer customer) {
        boolean shouldAddCustomer = shouldAdd(customer);
        if (shouldAddCustomer) {
            customers.add(customer);
        }
        return shouldAddCustomer;
    }

    public void handleRequest(CustomerRequest request) throws InsufficientFundException {
        findHandler(request.getType()).handle(request);
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
}
