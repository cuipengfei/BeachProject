package com.second.job.tw;

import com.second.job.tw.handle.CustomerHandler;
import com.second.job.tw.handle.DespoitHandler;
import com.second.job.tw.handle.WithdrawHandler;
import com.second.job.tw.request.CustomerRequest;
import com.second.job.tw.request.RequestType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppyao on 8/12/15.
 */
public class Bank {
    LinkedList<Customer> customerLinkedList = new LinkedList<Customer>();
    static Map<RequestType, CustomerHandler> customerHandlerMap = new HashMap<RequestType, CustomerHandler>();

    static {
        customerHandlerMap.put(RequestType.depositMoney, new DespoitHandler());
        customerHandlerMap.put(RequestType.withdrawMoney, new WithdrawHandler());
    }

    public boolean AddCustomertoBankwhenValid(Customer customer) {
        if (validateNickname(customer) && isCustomerNotRepeat(customer)) {
            customerLinkedList.add(customer);
            sendWelcomeMessage(customer);
            return true;
        }
        return false;
    }

    private Mail sendWelcomeMessage(Customer customer) {
        String message = "Dear <" + customer.getNickname() + ">,Welcome to the Bank";
        if (validateEmail(customer)) {
            Mail mail = new Mail(customer, message);
            customer.setMail(mail);
            return mail;
        }
        return null;
    }


    private boolean isCustomerNotRepeat(Customer customer) {
        for (Customer customer1 : customerLinkedList) {
            if (customer.getNickname().equals(customer1.getNickname())) {
                return false;
            }
        }
        return true;
    }

    private boolean validateNickname(Customer customer) {
        final String strRegex = "^[a-z0-9]+$";
        Pattern pattern = Pattern.compile(strRegex);
        Matcher matcher = pattern.matcher(customer.getNickname());
        return matcher.find();
    }

    private boolean validateEmail(Customer customer) {
        return customer.getEmailAddress().matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z-]+([.][a-z-]+)*$");

    }

    public void handleRequest(CustomerRequest request) throws OverdraftException {

        if (customerLinkedList.contains(request.getCustomer())) {
            customerHandlerMap.get(request.getType()).handlers(request);
        }
    }
}
