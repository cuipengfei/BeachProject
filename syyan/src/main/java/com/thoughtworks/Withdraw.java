package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;

public class Withdraw implements Handler {
    @Override
    public double handle(CustomerRequest customerRequest) throws OverdrawException {

        Customer customer = customerRequest.getCustomer();
        double money = customerRequest.getBalance();

        if (customer.getBalance() < customerRequest.getBalance()) throw new OverdrawException();
        customer.setBalance(customer.getBalance() - money);

        return customer.getBalance();

    }
}
