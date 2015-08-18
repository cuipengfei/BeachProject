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
    MessageGateway fasterMessageGateway = new FasterMessageGateway();
    LinkedList<Customer> customerLinkedList = new LinkedList<Customer>();
    BankManager bankManager = new BankManager();
    static Map<RequestType, CustomerHandler> customerHandlerMap = new HashMap<RequestType, CustomerHandler>();

    public Bank(MessageGateway fasterMessageGateway) {
        this.fasterMessageGateway = fasterMessageGateway;
    }

    static {
        customerHandlerMap.put(RequestType.depositMoney, new DespoitHandler());
        customerHandlerMap.put(RequestType.withdrawMoney, new WithdrawHandler());
    }

    public boolean AddCustomertoBankwhenValid(Customer customer) {
        if (validateNickname(customer) && isCustomerNotRepeat(customer)) {
            customerLinkedList.add(customer);
            String message = "Dear <" + customer.getNickname() + ">,Welcome to the Bank";
            fasterMessageGateway.sendEmail(customer.getEmailAddress(), message);
            return true;
        }
        return false;
    }

    public void handleRequest(CustomerRequest request) throws OverdraftException {

        if (customerLinkedList.contains(request.getCustomer())) {
            customerHandlerMap.get(request.getType()).handlers(request);
            if (isPrminumCustomer(request.getCustomer())) {
                fasterMessageGateway.sendEmail(bankManager.getEmailAddress(), request.getCustomer().getNickname() + " is a premium customer");
                bankManager.getPrminumCustomerList().add(request.getCustomer());
                request.getCustomer().setIsPreminumDefault(true);
            }
        }
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

    private boolean isPrminumCustomer(Customer customer) {
        if (customer.getAccount().getBalance() >= 40000 && !(customer.isPreminum())) {
            return true;
        }
        return false;

    }
}
