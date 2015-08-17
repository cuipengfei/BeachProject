package com.second.job.tw;

import org.junit.Test;

import java.util.Date;

import static com.second.job.tw.request.CustomerRequest.despoitRequst;
import static com.second.job.tw.request.CustomerRequest.withdrawRequest;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ppyao on 8/12/15.
 */
public class BankTest {
    @Test
    public void bankAcceptValidCustomer() {
        MailSender sender = new MailSender();
        //given
        Bank bank = new Bank(sender);
        Customer customer = new Customer("yaoping", new Date());
        //when
        boolean isSuccess = bank.AddCustomertoBankwhenValid(customer);
        //then
        assertTrue(isSuccess);

    }

    @Test
    public void bankShouldUnacceptCustomerWhenNicknameInValidate() {
        //given
        MailSender sender = new MailSender();
        Bank bank = new Bank(sender);
        Customer customer = new Customer("Yaoping", new Date());
        //when
        boolean isSuccess = bank.AddCustomertoBankwhenValid(customer);

        // then
        assertFalse(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenCustomerExist() {
        //given
        MailSender sender = new MailSender();
        Bank bank = new Bank(sender);
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
        MailSender sender = new MailSender();
        Bank bank = new Bank(sender);
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
        MailSender sender = new MailSender();
        Bank bank = new Bank(sender);
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
        MailSender sender=new MailSender();
        Bank bank = new Bank(sender);
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
        MailSender sender=new MailSender();
        Bank bank = new Bank(sender);
        Customer customer = new Customer("yaoping", new Date());
        bank.AddCustomertoBankwhenValid(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 150.0));
    }

    @Test
    public void bandShouldNotAcceptAnyRequestWhenCustomerNotAdd() throws OverdraftException {
        //  given
        MailSender sender=new MailSender();
        Bank bank = new Bank(sender);
        Customer customer = new Customer("yaoping", new Date());
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));

    }

    @Test
    public void bandActualSendEamil() {
        MailSender sender = mock(MailSender.class);
        Bank bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", new Date());
        String message = "Dear <" + customer.getNickname() + ">,Welcome to the Bank";

        bank.AddCustomertoBankwhenValid(customer);
        verify(sender).sendEmail(customer, message);
    }

    @Test
    public void bankActualSendEmailImitationMockito()
    {
        MailSendMockito sender=new MailSendMockito();
        Bank bank=new Bank(sender);
        Customer customer=new Customer("yaopingping",new Date());
        bank.AddCustomertoBankwhenValid(customer);

        assertThat(sender.isSendMailCalled(),is(true));
    }

}