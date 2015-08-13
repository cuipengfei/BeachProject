package com.second.job.tw;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by ppyao on 8/12/15.
 */
public class BankTest {
    @Test
    public void bankAcceptValidCustomer()
    {
        //given
        Bank bank=new Bank();
        Customer customer=new Customer("yaoping",new Date());
        //when
        boolean isSuccess=bank.isAddCustomerValid(customer);
        //then
        assertTrue(isSuccess);
    }
    @Test
    public void bankShouldUnacceptCustomerWhenNicknameValid()
    {
        //given
        Bank bank=new Bank();
        Customer customer=new Customer("Yaoping",new Date());
        //when
        boolean isSuccess=bank.isAddCustomerValid(customer);
        //then
        assertFalse(isSuccess);

    }
    @Test
    public void bankShouldUnacceptCustomerWhenCustomerExist()
    {
        //given
        Bank bank=new Bank();
        Customer firstCustomer=new Customer("yaoping",new Date());
        Customer secondCustomer=new Customer("yaoping",new Date());
        //when
        boolean isFirstSuccess=bank.isAddCustomerValid(firstCustomer);
        boolean isSecondSuccess=bank.isAddCustomerValid(secondCustomer);
        assertTrue(isFirstSuccess);
        assertFalse(isSecondSuccess);
    }
    @Test
    public void bankShouldDespoitMoney()
    {
        //given
        Bank bank=new Bank();
        Customer customer=new Customer("yaoping",new Date());
        bank.isAddCustomerValid(customer);
        //when
        double despoitMoney=bank.despoitMoney(customer, 100.0);
        //then
        assertThat(customer.getAccount().getBalance(), is(100.0));
    }
    @Test
    public void bankShouldWithdrawMoneyWhenMoneyLessThanBalance () throws Exception {
        //given
        Bank bank=new Bank();
        Customer customer=new Customer("yaoping",new Date());
        bank.isAddCustomerValid(customer);
        //when
        bank.despoitMoney(customer,100.0);
        double withdrawMoney=bank.withdrawMoney(customer, 50.0);

        //then
        assertThat(customer.getAccount().getBalance(),is(50.0));
    }
    @Test(expected = OverdraftException.class)
    public void bankShouldNotWithdrawMoneyWhenMoneyLargerThanBalance () throws OverdraftException {
        //given
        Bank bank=new Bank();
        Customer customer=new Customer("yaoping",new Date());
        bank.isAddCustomerValid(customer);
        //when
        bank.despoitMoney(customer,100.0);
        double withdrawMoney=bank.withdrawMoney(customer,150.0);


    }

}