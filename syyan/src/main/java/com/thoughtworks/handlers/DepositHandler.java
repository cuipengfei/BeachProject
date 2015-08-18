package main.java.com.thoughtworks.handlers;

import main.java.com.thoughtworks.Customer;
import main.java.com.thoughtworks.requests.CustomerRequest;

public class DepositHandler implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        Customer customer = customerRequest.getCustomer();
        customer.setBalance(customer.getBalance() + customerRequest.getBalance());
        return customer.getBalance();
    }
}
