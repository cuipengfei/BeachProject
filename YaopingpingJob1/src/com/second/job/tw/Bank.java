package com.second.job.tw;

import com.second.job.tw.request.CustomerRequest;
import com.second.job.tw.request.RequestType;
import handle.CustomerHandler;
import handle.Handlers;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.org.apache.bcel.internal.generic.InstructionList.findHandle;

/**
 * Created by ppyao on 8/12/15.
 */
public class Bank {
    LinkedList<Customer> customerLinkedList=new LinkedList<Customer>();

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
            Handlers.findHandle(request.getType()).handlers(request);
        }
    }
}
