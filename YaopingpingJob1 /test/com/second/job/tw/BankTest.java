package com.second.job.tw;

import com.second.job.tw.request.CustomerRequest;
import com.second.job.tw.request.RequestType;
import org.junit.Test;

import java.util.Date;

import static com.second.job.tw.request.CustomerRequest.despoitRequst;
import static com.second.job.tw.request.CustomerRequest.withdrawRequest;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by ppyao on 8/12/15.
 */
public class BankTest {
    @Test
    public void bankAcceptValidCustomer() {
        //given
        Bank bank = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        //when
        boolean isSuccess = bank.AddCustomertoBankwhenValid(customer);
        //then
        assertTrue(isSuccess);

    }
    @Test
    public void bankShouldUnacceptCustomerWhenNicknameInValidate() {
        //given
        Bank bank = new Bank();
        Customer customer = new Customer("Yaoping", new Date());
        //when
        boolean isSuccess = bank.AddCustomertoBankwhenValid(customer);

        // then
        assertFalse(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenCustomerExist() {
        //given
        Bank bank = new Bank();
        Customer firstCustomer = new Customer("yaoping", new Date());
        Customer secondCustomer = new Customer("yaoping", new Date());
        //when
        boolean isFirstSuccess = bank.AddCustomertoBankwhenValid(firstCustomer);
        boolean isSecondSuccess = bank.AddCustomertoBankwhenValid(secondCustomer);
        assertTrue(isFirstSuccess);
        assertFalse(isSecondSuccess);
    }

    @Test
    public void bankShouldDespoitMoney() throws OverdraftException {
        //given
        Bank bank = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(100.0));
    }

    @Test
    public void bankShouldNotAcceptDespoitMoneyWhenMoneyLessThanZero() throws OverdraftException {
        //given
        Bank bank = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, -10.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));
    }

    @Test
    public void bankShouldWithdrawMoneyWhenMoneyLessThanBalance() throws Exception {
        //given
        Bank bank = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 50.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(50.0));
    }

    @Test(expected = OverdraftException.class)
    public void bankShouldNotWithdrawMoneyWhenMoneyLargerThanBalance() throws OverdraftException {
        //given
        Bank bank = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 150.0));
    }

    @Test
    public void bandShouldNotAcceptAnyRequestWhenCustomerNotAdd() throws OverdraftException {
        //  given
        Bank bank = new Bank();
        Customer customer = new Customer("yaoping", new Date());
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));

    }

}