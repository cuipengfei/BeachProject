package com.second.job.tw;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public double despoitMoney(Customer customer, double money) {
        if(money<0)
            return customer.getAccount().getBalance();
        else
        {
            Double currentBalance=customer.getAccount().addBalance(money);
            customer.getAccount().setBalance(currentBalance);
            return currentBalance;
        }
    }
    public double withdrawMoney(Customer customer,double money) throws OverdraftException
    {
        double withdrawBalance=customer.getAccount().minusBalance(money);
        if(withdrawBalance>=0)
        {
            customer.getAccount().setBalance(withdrawBalance);
            return withdrawBalance;
        }else throw new OverdraftException();

    }

}
