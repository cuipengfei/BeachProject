package beach.utils;

import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientFundException;

import java.util.ArrayList;
import java.util.List;
import static beach.utils.handlers.Handlers.findHandler;

/**
 * Created by mlding on 8/11/15.
 */
public class Bank {

    List<Customer> customerList = new ArrayList<Customer>();

    private boolean IsNameRepeat(Customer customer) {
        for (Customer temp : customerList) {
            if (temp.getNickname().equals(customer.getNickname()))
                return true;
        }
            return false;
    }

    private boolean IsContains(Customer customer) {
        boolean Namelegal = (customer != Customer.invalidCustomer());
        boolean Isrepeat = IsNameRepeat(customer);
        return Namelegal && !Isrepeat;
    }

    public boolean addCustomer(Customer customer){
        boolean shouldAddCustomer = IsContains(customer);
        if (shouldAddCustomer)
            customerList.add(customer);
        return shouldAddCustomer;
    }

    public void handleRequest(CustomerRequest request) throws InsufficientFundException{
        if (customerList.contains(request.getCustomer())){
            findHandler(request.getType()).handle(request);
        }
    }
}

