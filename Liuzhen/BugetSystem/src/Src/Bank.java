package Src;

import Handler.Handlers;
import MyException.CustomerNotExistException;
import Request.CustomerRequest;
import java.util.LinkedList;
import java.util.List;

public class Bank {
    private List<Customer> customerList = new LinkedList<>();
    private MailSender mailSender;
    public Bank(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    public boolean add(Customer _customer) {
        if (shouldAdd(_customer)) {
            customerList.add(_customer);
            sendWelcomeMessage(_customer);
        }

        return shouldAdd(_customer);
    }

    public void handleRequest(CustomerRequest _request) throws Exception{
        if (customerList.contains(_request.getCustomer())){
            Handlers.findHandler(_request.getRequestType()).handle(_request);
        }
        else throw new CustomerNotExistException();
    }

    private void sendWelcomeMessage(Customer _customer){
        mailSender.sendEmail("thebankmanager@thebank.com", _customer.getNickName() + "@thebank.com", "Welcome Message", "Dear " + _customer.getNickName() + ", Welcome to the Bank!");
    }

    private boolean shouldAdd(Customer _customer) {
        boolean isNotValidNickName = (_customer != Customer.getInvalidCustomer());
        boolean isExistName = isExistName(_customer);
        return  isNotValidNickName && !isExistName;
    }

    private boolean isExistName(Customer _customer) {
        boolean isExistName = false;
        for(Customer customer: customerList) {
            if (customer.getNickName().equals(_customer.getNickName()) )
                isExistName = true;
        }
        return isExistName;
    }

}

