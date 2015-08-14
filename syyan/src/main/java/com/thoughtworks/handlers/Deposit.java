package main.java.com.thoughtworks.handlers;

import main.java.com.thoughtworks.Customer;
import main.java.com.thoughtworks.exception.OverdrawException;
import main.java.com.thoughtworks.requests.CustomerRequest;

public class Deposit implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) throws OverdrawException {
        Customer customer = customerRequest.getCustomer();
        customer.setBalance(customer.getBalance() + customerRequest.getBalance());
        return customer.getBalance();
    }
}
