package com.second.job.tw;

import com.second.job.tw.request.CustomerRequest;
import com.second.job.tw.request.RequestType;
import handle.CustomerHandler;
import handle.DespoitHandler;
import handle.WithdrawHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppyao on 8/12/15.
 */
public class Bank {
    LinkedList<Customer> customerLinkedList=new LinkedList<Customer>();
    static Map<RequestType,CustomerHandler> customerHandlerMap=new HashMap<RequestType,CustomerHandler>();
    static {
        customerHandlerMap.put(RequestType.depositMoney, new DespoitHandler());
        customerHandlerMap.put(RequestType.withdrawMoney,new WithdrawHandler());
    }

    public boolean isAddCustomerValid(Customer customer)
    {
        if(validateNickname(customer)&&isCustomerNotRepeat(customer))
        {
            customerLinkedList.add(customer);
            return true;
        }
        return false;
    }

    private   boolean isCustomerNotRepeat(Customer customer)
    {
        for(Customer customer1:customerLinkedList)
        {

            if(customer1.getNickname().equals(customer.getNickname()))
            {
                return false;

            }

        }
        return true;
    }

    private   boolean validateNickname(Customer customer )
    {
        final String strRegex="^[a-z0-9]+$";
        Pattern pattern=Pattern.compile(strRegex);
        Matcher matcher=pattern.matcher(customer.getNickname());
        return matcher.find();
    }

    public void handleRequest(CustomerRequest request) throws OverdraftException {

        if (customerLinkedList.contains(request.getCustomer())) {
            customerHandlerMap.get(request.getType()).handlers(request);
        }
    }
}
