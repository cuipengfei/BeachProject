package beach.tw.entity;

import beach.tw.external.MessageGateway;
import beach.tw.external.Status;
import beach.tw.handlers.Handlers;
import beach.tw.requests.CustomerRequest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    private Status sendFlag = Status.FAILED;

    public Bank(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    public void handleRequest(CustomerRequest request) {
        if (customerList.contains(request.getCustomer())) {
            Handlers.findHandler(request.getType()).handle(request);

            Boolean flag = request.getCustomer().isPremiumCustomer();
            if (request.getCustomer().getAccount().getMoney() > 40000 && !flag) {
                messageGateway.sendMail("manager@thebank.com", request.getCustomer().getName() + " is now a premium customer");
                request.getCustomer().setIsPremiumCustomer(true);
            }
        }
    }

    public boolean addCustomer(Customer customer) {
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
        customer.setAccount(new Account());
        customer.setJoiningDate(Calendar.getInstance());
        return customer;
    }

    private void sendMessage(Customer customer) {
        String address = customer.getName() + "@thebank.com";
        String message = "Dear " + customer.getName() + ", Welcome to the Bank";
        messageGateway.sendMail(address, message);
        sendFlag = Status.OK;
    }

    private void logMessage(Customer customer) {
        try {
            String filePath = this.getClass().getClassLoader().getResource("log/CustomerMessage").getPath();
            System.out.println(filePath);
            File file = new File(filePath);
            if (!file.exists()) { file.createNewFile(); }
            String data = "Customer Name: " + customer.getName() + "  a Log is recorded when the gateway Status is " + sendFlag + "  " + new Date() + "\n";
            System.out.println(data);
            FileWriter fileWriter = new FileWriter(file.getName(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
