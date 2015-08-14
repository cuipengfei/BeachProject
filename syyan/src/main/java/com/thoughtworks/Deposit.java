package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;

public class Deposit implements  Handler {
    @Override
    public double handle(CustomerRequest customerRequest) throws OverdrawException {
        Customer customer = customerRequest.getCustomer();
        customer.setBalance(customer.getBalance() + customerRequest.getBalance());
        return customer.getBalance();
    }
}
