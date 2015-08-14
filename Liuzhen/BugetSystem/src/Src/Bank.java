package Src;

import Handler.Handlers;
import Request.CustomerRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhenliu on 8/11/15.
 */
public class Bank {
    List<Customer> customerList = new LinkedList<>();

    public boolean add(Customer _customer) {
        boolean shouldAddCustomer = shouldAdd(_customer);

        if (shouldAddCustomer) {
            _customer.setAccount(0.0);
            customerList.add(_customer);
        }
        if (shouldAddCustomer)
            System.out.println("shouldAdd");
        else
            System.out.println("shouldNotAdd");

        return shouldAddCustomer;

    }

    public void handleRequest(CustomerRequest request) throws Exception{
        if (customerList.contains(request.getCustomer())){
            Handlers.findHandler(request.getRequestType()).handle(request);
        }
        else throw new Exception("this customer dose not exist!");
    }

    private boolean shouldAdd(Customer _customer) {
        boolean isNotValidNickName = (_customer != Customer.getInvalidCustomer());
        boolean isExistName = isExistName(_customer);
        return  isNotValidNickName && !isExistName;
    }

    public boolean isExistName(Customer _customer) {
        boolean isExistName = false;
        for(Customer customer: customerList) {
            if (customer.getNickName().equals(_customer.getNickName()) )
                isExistName = true;
        }
        return isExistName;
    }

}

