package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;
import main.java.com.thoughtworks.requests.RequestType;

import java.util.*;
import java.util.regex.Pattern;

import static main.java.com.thoughtworks.handlers.Handlers.findHandler;


public class Bank {

    private List<Customer> customerList = new ArrayList<>();


    public boolean addCustomer(Customer customer) {
        if (!isExist(customer) && isValid(customer.getNickName())) {
            customerList.add(customer);
            return true;
        }
        return false;
    }

    public void handleRequest(CustomerRequest request) throws OverdrawException {
        if (customerList.contains(request.getCustomer()))
            findHandler(request.getRequestType()).handle(request);
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

    public static class CustomerRequest {
        private Customer customer;
        private RequestType requestType;
        private double balance;

        public Customer getCustomer() {
            return customer;
        }

        public RequestType getRequestType() {
            return requestType;
        }

        public double getBalance() {
            return balance;
        }

        public CustomerRequest(Customer customer, RequestType requestType, double balance) {
            this.customer = customer;
            this.requestType = requestType;
            this.balance = balance;
        }

        public static CustomerRequest withdraw(Customer customer, double balance) {
            return  new CustomerRequest(customer,RequestType.Withdraw,balance);
        }
        public static CustomerRequest deposit(Customer customer, double balance) {
            return  new CustomerRequest(customer,RequestType.Deposit,balance);
        }
    }
}
