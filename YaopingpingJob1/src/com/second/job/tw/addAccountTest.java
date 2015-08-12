package com.second.job.tw;

import java.util.Date;

/**
 * Created by ppyao on 8/12/15.
 */
public class addAccountTest {
    public static void main(String [] args) throws Exception {
        Bank customerAdministator = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        Account account = customerAdministator.addCustomertoBank(customer, 443);
        customer.depositMoney(account, 22);
        try {
            customer.withdrawMoney(account,410);
            customer.withdrawMoney(account,50);
            customer.withdrawAllMoney(account);
            customer.withdrawMoney(account,10);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Customer customer1 = new Customer("yaoping", new Date());
        Account account1 = customerAdministator.addCustomertoBank(customer1, 100);
        Customer customer2 = new Customer("YAO", new Date());
        Account account2 = customerAdministator.addCustomertoBank(customer2, 100);
    }
}
