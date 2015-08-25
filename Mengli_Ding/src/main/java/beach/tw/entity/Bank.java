package beach.tw.entity;

import beach.tw.external.MessageGateway;
import beach.tw.external.Status;
import beach.tw.handlers.Handlers;
import beach.tw.requests.CustomerRequest;
import beach.tw.utils.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mlding on 8/15/15.
 */
public class Bank {
    private List<Customer> customerList = new ArrayList<>();
    private MessageGateway messageGateway;
    private Status sendFlag;
    private boolean isCalls;

    public boolean isCalls() {
        return isCalls;
    }

    public Bank(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    public void handleRequest(CustomerRequest request) {
        if (customerList.contains(request.getCustomer())) {
            Handlers.findHandler(request.getType()).handle(request);

            Boolean flag = request.getCustomer().isPremiumCustomer();
            if (request.getCustomer().getAccount("current").getMoney() > 40000 && !flag) {
                messageGateway.sendMail("manager@thebank.com", request.getCustomer().getName() + " is now a premium customer");
                request.getCustomer().setIsPremiumCustomer(true);
            }
        }
    }

    public boolean addCustomer(Customer customer) throws IOException {
        boolean isShouldAdd = isShouleAdd(customer);
        if (isShouldAdd) {
            customerList.add(initCustomer(customer));
            sendMessage(customer);
            logMessage(customer);
        }
        return isShouldAdd;
    }

    private boolean isShouleAdd(Customer customer) {
        boolean customerIsValid = customer != Customer.invalidCustomer();
        boolean customerIsNotSameName = isNotSameName(customer);
        return customerIsValid && customerIsNotSameName;
    }

    private boolean isNotSameName(Customer customer) {
        for (Customer customertemp : customerList) {
            if (customertemp.getName().equals(customer.getName()))
                return false;
        }
        return true;
    }

    private Customer initCustomer(Customer customer) {
        Account current = new Account("current");
        customer.getAccountList().add(current);
        customer.setAccount(current);
        customer.setJoiningDate(Calendar.getInstance());
        return customer;
    }

    private void sendMessage(Customer customer) {
        String address = customer.getName() + "@thebank.com";
        String message = "Dear " + customer.getName() + ", Welcome to the Bank";
        try {
            sendFlag = messageGateway.sendMail(address, message);
        } catch (Exception e) {
            sendFlag = Status.EXCEPTION;
        }
    }

    private void logMessage(Customer customer) {
        String data = "Customer Name: " + customer.getName() + "  a Log is recorded when the gateway Status is " + sendFlag + "  " + new Date() + "\n";
        FileUtil.writeMessage(data);
        isCalls = true;
    }

}
