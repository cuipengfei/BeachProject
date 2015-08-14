package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;

import java.util.*;
import java.util.regex.Pattern;


public class Bank {

    private List<Customer> customerList = new ArrayList<>();


    public boolean addCustomer(Customer customer) {
        if (!isExist(customer) && isValid(customer.getNickName())) {
            customerList.add(customer);
            return true;
        }
        return false;
    }

    private boolean isValid(String nickName) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+$");
        return pattern.matcher(nickName).matches();
    }

    private boolean isExist(Customer customer) {
        for (Customer temp : customerList) {
            if (temp.getNickName().equals(customer.getNickName())) {
                return true;
            }
        }
        return false;
    }

    private double withdraw(Customer customer, double money) throws OverdrawException {
        if (customerList.contains(customer)) {
            if (customer.getBalance() < money) throw new OverdrawException();
            customer.setBalance(customer.getBalance() - money);
        }
        return customer.getBalance();
    }

    private double deposit(Customer customer, double money) {
        if (customerList.contains(customer)) {
            customer.setBalance(customer.getBalance() + money);
        }
        return customer.getBalance();
    }

    public double handle(Customer customer, RequestType requestType, double balance) throws OverdrawException {
        if (requestType == RequestType.Deposit)
            return deposit(customer, balance);
        else return withdraw(customer, balance);
    }
}
