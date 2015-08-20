package beach.tw.entity;

import beach.external.MessageGateway;
import beach.tw.handlers.Handlers;
import beach.tw.requests.CustomerRequest;

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

    public Bank(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    private boolean isNotSameName(Customer customer) {
        for (Customer customertemp : customerList) {
            if (customertemp.getName().equals(customer.getName()))
                return false;
        }
        return true;
    }

    private boolean isShouleAdd(Customer customer) {
        boolean customerIsValid = customer != Customer.invalidCustomer();
        boolean customerIsNotSameName = isNotSameName(customer);
        return customerIsValid && customerIsNotSameName;

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
            customerList.add(customer);
            messageGateway.sendMail(customer.getName() + "@thebank.com", "Dear " + customer.getName() + ", Welcome to the Bank");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            customer.setJoiningDate(calendar);
        }
        return isShouldAdd;
    }
}
