package beach.utils;

import beach.external.MessageGateway;
import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static beach.utils.handlers.Handlers.findHandler;

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

    public void handleRequest(CustomerRequest request) throws InsufficientException {
        if (customerList.contains(request.getCustomer())) {
            findHandler(request.getType()).handle(request);

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
            customer.setJoiningDate(new Date());
        }
        return isShouldAdd;
    }

    private boolean IsCustomerBeenWithBankOverTwoYears(Customer customer){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(customer.getJoiningDate());
        calendar.add(Calendar.YEAR, 2);
        return calendar.before(new Date());
    }

    public void addBonus(Customer customer){
        int balance = customer.getAccount().getMoney();
        if (IsCustomerBeenWithBankOverTwoYears(customer) && balance >0)
            balance += 5;
    }

}
