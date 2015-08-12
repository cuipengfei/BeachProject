package com.second.job.tw;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppyao on 8/12/15.
 */
public class Bank {
    LinkedList<Account> customerLinkedList=new LinkedList<Account>();
    Account potentialAccount;
    public Account addCustomertoBank(Customer customer,double balance)
    {
        if(validateNickname(customer)&&isCustomerNotRepeat(customer))
        {
            potentialAccount=new Account(customer.getNickname(),customer.getDateofBirth(),balance);
            customerLinkedList.add(potentialAccount);
            System.out.println("开户成功");
            System.out.println("开户本金"+potentialAccount.getBalance());
            return potentialAccount;
        }
        else
        {
            System.out.println("输入的账户名不准确或者是该用户已经存在");
            return null;
        }


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


}
