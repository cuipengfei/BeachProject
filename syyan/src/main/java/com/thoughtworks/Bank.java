package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;

import java.util.*;
import java.util.regex.Pattern;


public class Bank {

    private List<Customer> customerList = new ArrayList<>();
    private final Map<Customer, Integer> accounts = new HashMap<>();

    public boolean addCustomer(Customer customer) {
        if (!contains(customer) && isValid(customer.getNickName())) {
            customerList.add(customer);
            return true;
        }
        return false;
    }

    private boolean isValid(String nickName) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+$");
        return pattern.matcher(nickName).matches();
    }

    private boolean contains(Customer customer) {
        for (Customer temp : customerList) {
            if (temp.getNickName().equals(customer.getNickName())) {
                return true;
            }
        }
        return false;
    }

    public int withdraw(Customer customer, int money) throws OverdrawException {
        if (customerList.contains(customer)) {
            if (accounts.get(customer) < money) throw new OverdrawException();
            if (accounts.containsKey(customer)) {
                accounts.put(customer, accounts.get(customer) - money);
            } else accounts.put(customer, money);
        } else accounts.put(customer, 0);
        return accounts.get(customer);
    }

    public int deposit(Customer customer, int money) {
        if (customerList.contains(customer)) {
            if (accounts.containsKey(customer)) {
                accounts.put(customer, accounts.get(customer) + money);
            } else accounts.put(customer, money);
        } else accounts.put(customer, 0);
        return accounts.get(customer);
    }

}
