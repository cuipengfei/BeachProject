package beach.utils;

import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientException;

import java.util.ArrayList;
import java.util.List;

import static beach.utils.handlers.Handlers.findHandler;

/**
 * Created by mlding on 8/15/15.
 */
public class Bank {
    private List<Customer> customerList = new ArrayList<>();
    private EmailSender emailSender;

    public Bank(){}

    public Bank(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    private boolean isNotSameName(Customer customer){
        for (Customer customertemp : customerList){
            if (customertemp.getName().equals(customer.getName()))
                return false;
        }
        return true;
    }

    private boolean isShouleAdd(Customer customer){
        boolean customerIsValid = customer != Customer.invalidCustomer();
        boolean customerIsNotSameName = isNotSameName(customer);
        return customerIsValid && customerIsNotSameName;

    }

    public void handleRequest(CustomerRequest request) throws InsufficientException {
        if (customerList.contains(request.getCustomer())){
            findHandler(request.getType()).handle(request);
        }
    }

    public boolean addCustomer(Customer customer){
        boolean isShouldAdd = isShouleAdd(customer);
        if (isShouldAdd){
            customerList.add(customer);
            emailSender.sendMail(customer);
        }
        return isShouldAdd;
    }

}
