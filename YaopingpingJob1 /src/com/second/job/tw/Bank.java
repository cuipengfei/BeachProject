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
    MailSender mailSender;
    LinkedList<Customer> customerLinkedList = new LinkedList<Customer>();
    BankManager bankManager=new BankManager();
    static Map<RequestType, CustomerHandler> customerHandlerMap = new HashMap<RequestType, CustomerHandler>();


    public Bank() {
    }

    public Bank(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    static {
        customerHandlerMap.put(RequestType.depositMoney, new DespoitHandler());
        customerHandlerMap.put(RequestType.withdrawMoney, new WithdrawHandler());
    }

    public boolean AddCustomertoBankwhenValid(Customer customer) {
        if (validateNickname(customer) && isCustomerNotRepeat(customer)) {
            customerLinkedList.add(customer);
            String message = "Dear <" + customer.getNickname() + ">,Welcome to the Bank";
            mailSender.sendEmail(customer, message);
            return true;
        }
        return false;
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

    public void handleRequest(CustomerRequest request) throws OverdraftException {

        if (customerLinkedList.contains(request.getCustomer())) {
           double balance= customerHandlerMap.get(request.getType()).handlers(request);
            if(balance>=40000&&request.getCustomer().isPreminum()==false)
            {
                String message=request.getCustomer().getNickname()+"is a premium customer";
                mailSender.sendEmail(bankManager,message);
                bankManager.getPrminumCustomerList().add(request.getCustomer());
                request.getCustomer().setIsPreminumDefault(true);
            }

        }
    }
}
