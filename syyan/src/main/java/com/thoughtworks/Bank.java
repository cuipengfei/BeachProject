package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;

import java.util.*;
import java.util.regex.Pattern;

import static main.java.com.thoughtworks.Handlers.findHandler;


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
}
