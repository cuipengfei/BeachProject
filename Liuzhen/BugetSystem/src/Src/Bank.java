package Src;

import Handler.Handlers;
import MyException.CustomerNotExistException;
import Request.CustomerRequest;
import java.util.LinkedList;
import java.util.List;

public class Bank {
    List<Customer> customerList = new LinkedList<>();

    public boolean add(Customer _customer) {
        if (shouldAdd(_customer)) {
            customerList.add(_customer);
        }

        return shouldAdd(_customer);
    }

    public void handleRequest(CustomerRequest request) throws Exception{
        if (customerList.contains(request.getCustomer())){
            Handlers.findHandler(request.getRequestType()).handle(request);
        }
        else throw new CustomerNotExistException();
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

