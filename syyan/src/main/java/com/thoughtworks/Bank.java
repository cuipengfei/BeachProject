package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;
import main.java.com.thoughtworks.requests.CustomerRequest;
import main.java.com.thoughtworks.requests.RequestType;

import java.util.*;
import java.util.regex.Pattern;

import static main.java.com.thoughtworks.handlers.Handlers.findHandler;


public class Bank {
    private EmailSender emailSender;

    private List<Customer> customerList = new ArrayList<>();

    public Bank(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public boolean addCustomer(Customer customer) {
        if (!isExist(customer) && isValid(customer.getNickName())) {
            customerList.add(customer);
            emailSender.sendMessage(customer.getNickName() + "@thebank.com", "Dear " + customer.getNickName() + ", Welcome to the Bank");
            return true;
        }
        return false;
    }

    public void handleRequest(CustomerRequest request) throws OverdrawException {
        if (customerList.contains(request.getCustomer()))
            findHandler(request.getRequestType()).handle(request);
        if (request.getRequestType() == RequestType.Deposit && !request.getCustomer().isPremiumCustomer() && shouldPremiumCustomer(request.getCustomer())) {
            emailSender.sendMessage("manager@thebank.com", request.getCustomer().getNickName() + " is now a premium customer");
        }
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

    private boolean shouldPremiumCustomer(Customer customer) {
        if (customer.getBalance() >= 40000)
            customer.setIsPremiumCustomer(true);
        return customer.isPremiumCustomer();
    }
}
