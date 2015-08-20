package com.thoughtworks;

import com.thoughtworks.exception.OverdrawException;
import com.thoughtworks.external.FasterMessageGateway;
import com.thoughtworks.requests.CustomerRequest;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BankTest {


    private FasterMessageGateway emailSender = mock(FasterMessageGateway.class);

    private Bank bank = new Bank(emailSender);

    @Test
    public void should_add_success_when_give_the_valid_information() {
        Customer customer = new Customer("syyan123", new Date());

        assertTrue(bank.addCustomer(customer));
    }

    @Test
    public void should_add_failure_when_give_the_valid_nickName() {
        Customer notValidCustomer = new Customer("YAN", new Date());

        assertFalse(bank.addCustomer(notValidCustomer));
    }

    @Test
    public void should_add_failure_when_give_the_same_nickName() {
        Customer customer = new Customer("syyan123", new Date());

        assertTrue(bank.addCustomer(customer));
        Customer existCustomer = new Customer("syyan123", new Date());

        assertFalse(bank.addCustomer(existCustomer));
    }

    @Test
    public void should_deposit_money_when_customer_is_valid() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 100d));

        assertThat(customer.getBalance(), is(100d));
    }

    @Test
    public void should_withdraw_money_when_balance_is_not_overdraw() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 100d));
        bank.handleRequest(CustomerRequest.withdraw(customer, 100d));

        assertThat(customer.getBalance(), is(0d));
    }

    @Test(expected = OverdrawException.class)
    public void should_not_withdraw_money_when_balance_is_overdraw() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 100d));
        bank.handleRequest(CustomerRequest.withdraw(customer, 200d));
    }

    @Test
    public void should_not_withdraw_or_deposit_money_when_customer_is_not_exist() {
        Customer unexist = new Customer("unexist", new Date());

        bank.handleRequest(CustomerRequest.withdraw(unexist, 100d));
        assertThat(unexist.getBalance(), is(0d));

        bank.handleRequest(CustomerRequest.deposit(unexist, 100));
        assertThat(unexist.getBalance(), is(0d));
    }


    @Test
    public void should_use_sendMessage_when_addCustomer() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);

        verify(emailSender).sendMessage("syyan123@thebank.com", "Dear syyan123, Welcome to the Bank");
    }

    @Test
    public void should_use_sendMessage_when_customer_balance_is_over_40000() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 40000d));

        verify(emailSender).sendMessage("manager@thebank.com", "syyan123 is now a premium customer");
    }

    @Test
    public void should_use_sendMessage_only_once_when_customer_is_deposit_or_withdraw() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 40000d));
        bank.handleRequest(CustomerRequest.deposit(customer, 10000d));
        bank.handleRequest(CustomerRequest.withdraw(customer, 50000d));
        bank.handleRequest(CustomerRequest.deposit(customer, 40000d));

        verify(emailSender, times(1)).sendMessage("manager@thebank.com", "syyan123 is now a premium customer");
    }

    @Test
    public void should_not_use_sendMessage_only_once_when_customer_is_not_over_40000() {
        Customer customer = new Customer("syyan123", new Date());

        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 10000d));

        verify(emailSender, never()).sendMessage("manager@thebank.com", "syyan123 is now a premium customer");
    }

    @Test
    public void should_set_dateOfJoin_when_customer_is_added() {
        Customer customer = new Customer("syyan123", new Date());
        Date date = new Date();

        bank.addCustomer(customer);

        assertThat(customer.getDateOfJoin(), is(date));
    }
}
