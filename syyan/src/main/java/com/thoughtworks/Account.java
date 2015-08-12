package main.java.com.thoughtworks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    private final Map<Customer, Integer> accounts = new HashMap<>();
    Bank bank = new Bank();

    public int withdraw(Customer customer, int money) throws Exception {
        if (accounts.get(customer) - money < 0) throw new Exception("Throw overdraw exception");
        if (accounts.containsKey(customer)) {
            accounts.put(customer, accounts.get(customer) - money);
        } else if (bank.addCustomer(customer)) accounts.put(customer, money);
        else throw new Exception("Throw invalid account exception");
        return accounts.get(customer);
    }

    public int deposit(Customer customer, int money) throws Exception {
        if (accounts.containsKey(customer)) {
            accounts.put(customer, accounts.get(customer) + money);
        } else if (bank.addCustomer(customer)) accounts.put(customer, money);
        else throw new Exception("Throw invalid account exception");
        return accounts.get(customer);
    }
}
