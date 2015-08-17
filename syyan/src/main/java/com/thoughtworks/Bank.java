package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;
import main.java.com.thoughtworks.requests.CustomerRequest;

import java.util.*;
import java.util.regex.Pattern;

import static main.java.com.thoughtworks.handlers.Handlers.findHandler;


public class Bank {
    private EmailSender emailSender;

    public Bank(EmailSender emailSender) {
        this.emailSender = emailSender;
    }


    private List<Customer> customerList = new ArrayList<>();

    public boolean addCustomer(Customer customer) {
        if (!isExist(customer) && isValid(customer.getNickName())) {
            customerList.add(customer);
            emailSender.sendMessage(customer.getNickName() + "@thebank.com","Dear " + customer.getNickName() + ", Welcome to the Bank");
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
