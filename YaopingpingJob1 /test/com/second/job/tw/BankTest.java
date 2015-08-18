package com.second.job.tw;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.second.job.tw.request.CustomerRequest.despoitRequst;
import static com.second.job.tw.request.CustomerRequest.withdrawRequest;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ppyao on 8/12/15.
 */
public class BankTest {
    FasterMessageGateway sender;
    Bank bank;

    @Before
    public void setUp() throws Exception {

        sender = new FasterMessageGateway();
        bank = new Bank(sender);

    }

    @Test
    public void bankAcceptValidCustomer() {
        //given
        Customer customer = new Customer("yaoping", new Date());
        //when
        boolean isSuccess = bank.AddCustomertoBankwhenValid(customer);

        //then
        assertTrue(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenNicknameInValidate() {
        Customer customer = new Customer("Yaoping", new Date());
        //when
        boolean isSuccess = bank.AddCustomertoBankwhenValid(customer);

        // then
        assertFalse(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenCustomerExist() {
        Customer firstCustomer = new Customer("yaoping", new Date());
        Customer secondCustomer = new Customer("yaoping", new Date());
        //when
        boolean isFirstSuccess = bank.AddCustomertoBankwhenValid(firstCustomer);
        boolean isSecondSuccess = bank.AddCustomertoBankwhenValid(secondCustomer);

        //then
        assertTrue(isFirstSuccess);
        assertFalse(isSecondSuccess);
    }

    @Test
    public void bankShouldDespoitMoney() throws OverdraftException {
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 5000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(5000.0));
    }

    @Test
    public void bankShouldNotAcceptDespoitMoneyWhenMoneyLessThanZero() throws OverdraftException {
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, -10.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));
    }

    @Test
    public void bankShouldWithdrawMoneyWhenMoneyLessThanBalance() throws Exception {
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
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 150.0));
    }

    @Test
    public void bandShouldNotAcceptAnyRequestWhenCustomerNotAdd() throws OverdraftException {
        Customer customer = new Customer("yaoping", new Date());
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));
    }

    @Test
    public void bandActualSendEamil() {
        //given
        FasterMessageGateway sender = mock(FasterMessageGateway.class);
        Bank bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", new Date());
        //when
        bank.AddCustomertoBankwhenValid(customer);

        //then
        verify(sender).sendEmail(customer.getEmailAddress(), "Dear <yaopingping>,Welcome to the Bank");
    }



    @Test
    public void bankShouldAddToPreminumWhenCustomerBalanceMoreThan40000() throws OverdraftException {
        //given
        FasterMessageGateway sender=mock(FasterMessageGateway.class);
        Bank bank=new Bank(sender);
        Customer customer = new Customer("yaopingping", new Date());
        //when
        bank.AddCustomertoBankwhenValid(customer);
        bank.handleRequest(despoitRequst(customer, 40000.0));

        //then
        verify(sender).sendEmail(bank.bankManager.getEmailAddress(), "yaopingping is a premium customer");
    }

    @Test
    public void bankShouldNotAddToPreminumWhenCustomerBalanceLessThan40000() throws OverdraftException {
        //given
        FasterMessageGateway sender = mock(FasterMessageGateway.class);
        Bank bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", new Date());
        //when
        bank.AddCustomertoBankwhenValid(customer);
        bank.handleRequest(despoitRequst(customer, 30000.0));

        //then
        verify(sender, times(0)).sendEmail(bank.bankManager.getEmailAddress(), "send message");
    }

    @Test
    public void bankShouldAddPreminumOnce() throws OverdraftException {
        //given
        FasterMessageGateway sender=mock(FasterMessageGateway.class);
        Bank bank=new Bank(sender);
        Customer customer=new Customer("yaoping",new Date());
        //when
        bank.AddCustomertoBankwhenValid(customer);
        bank.handleRequest(despoitRequst(customer, 40000.0));
        bank.handleRequest(despoitRequst(customer, 10000.0));

        verify(sender,times(1)).sendEmail(bank.bankManager.getEmailAddress(),"yaoping is a premium customer");
    }

}