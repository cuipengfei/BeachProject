package beach.tw.entity;

import beach.external.MessageGateway;
import beach.tw.handlers.Handlers;
import beach.tw.requests.CustomerRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void handleRequest(CustomerRequest request){
        if (customerList.contains(request.getCustomer())) {
            Handlers.findHandler(request.getType()).handle(request);

            Boolean flag = request.getCustomer().isPremiumCustomer();
            if (request.getCustomer().getAccount().getMoney() > 40000 && !flag) {
                messageGateway.sendMail("manager@thebank.com", request.getCustomer().getName() + " is now a premium customer");
                request.getCustomer().setIsPremiumCustomer(true);
            }
        }
    }

    public boolean addCustomer(Customer customer) throws ParseException {
        boolean isShouldAdd = isShouleAdd(customer);
        if (isShouldAdd) {
            customerList.add(customer);
            messageGateway.sendMail(customer.getName() + "@thebank.com", "Dear " + customer.getName() + ", Welcome to the Bank");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            customer.setJoiningDate(sdf.parse("1993-12-23"));
        }
        return isShouldAdd;
    }

    private boolean IsCustomerBeenWithBankOverTwoYears(Customer customer){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(customer.getJoiningDate());
        calendar2.setTime(new Date());
        calendar1.add(Calendar.YEAR, 2);
        return calendar1.before(calendar2);
    }

    public void addBonus(Customer customer){
        if (IsCustomerBeenWithBankOverTwoYears(customer) && customer.isActivated())
            customer.getAccount().add(5);
    }

}
