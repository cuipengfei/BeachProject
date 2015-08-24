package bank;

import customer.Customer;
import handler.Handlers;
import myException.CustomerNotExistException;
import request.CustomerRequest;
import mailsender.MailSender;
import mailsender.MailSenderStatusType;
import utils.WriteLog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Bank {
    private List<Customer> customerList = new LinkedList<>();
    private MailSender mailSender;

    public Bank(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean add(Customer customer) throws IOException {
        if (shouldAdd(customer)) {
            customerList.add(customer);
            customer.setJoiningDate(Calendar.getInstance());
            sendWelcomeMessage(customer);
        }

        return shouldAdd(customer);
    }

    public void handleRequest(CustomerRequest request) throws Exception {
        if (customerList.contains(request.getCustomer())) {
            Handlers.findHandler(request.getRequestType()).handle(request);

            if (request.getCustomer().getAccount(request.getAccountName()).getBalance() >= 40000.0 && !request.getCustomer().isPremiumCustomer()) {
                mailSender.sendEmail("manager@thebank.com", request.getCustomer() + " is now a premium customer.");
                request.getCustomer().setIsPremiumCustomer(true);
            }
        } else throw new CustomerNotExistException();
    }

    private void sendWelcomeMessage(Customer customer) throws IOException {
        MailSenderStatusType status = mailSender.sendEmail(customer.getNickName() + "@thebank.com", "Dear " + customer.getNickName() + ", Welcome to the bank!");
        writeCustomerMessageLog(customer.getNickName(), status);
    }

    private void writeCustomerMessageLog(String customerNickName, MailSenderStatusType status) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String logMessage = "-----------" + simpleDateFormat.format(new Date()) + "------------\n" + String.format("Mail sender's status is %s when send message to %s \n", status.toString(), customerNickName);
        WriteLog.writeLogToFile(logMessage);
    }

    private boolean shouldAdd(Customer _customer) {
        boolean isNotValidNickName = (_customer != Customer.getInvalidCustomer());
        boolean isExistName = isExistName(_customer);

        return isNotValidNickName && !isExistName;
    }

    private boolean isExistName(Customer _customer) {
        boolean isExistName = false;

        for (Customer customer : customerList) {
            if (customer.getNickName().equals(_customer.getNickName()))
                isExistName = true;
        }

        return isExistName;
    }
}

