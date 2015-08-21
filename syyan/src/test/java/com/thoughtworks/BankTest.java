package com.thoughtworks;

import com.thoughtworks.exception.NotExistCustomerException;
import com.thoughtworks.exception.OverdrawException;
import com.thoughtworks.external.FasterMessageGateway;
import com.thoughtworks.requests.CustomerRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BankTest {

    private FasterMessageGateway emailSender;
    private Bank bank;
    private Customer customer;
    private Customer inValidCustomer;

    @Before
    public void setUp() throws Exception {
        emailSender = mock(FasterMessageGateway.class);
        bank = new Bank(emailSender);
        customer = new Customer("syyan123",Calendar.getInstance());
        inValidCustomer = new Customer("YAN",Calendar.getInstance());
    }

    @Test
    public void should_add_success_when_give_the_valid_information() {
        assertTrue(bank.addCustomer(customer));
    }

    @Test
    public void should_add_failure_when_give_the_valid_nickName() {
        assertFalse(bank.addCustomer(inValidCustomer));
    }

    @Test
    public void should_add_failure_when_give_the_same_nickName() {
        assertTrue(bank.addCustomer(customer));
        Customer existCustomer = new Customer("syyan123",Calendar.getInstance());

        assertFalse(bank.addCustomer(existCustomer));
    }

    @Test
    public void should_deposit_money_when_customer_is_valid() {
        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 100d));

        assertThat(customer.getBalance(), is(100d));
    }

    @Test
    public void should_withdraw_money_when_balance_is_not_overdraw() {
        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 100d));
        bank.handleRequest(CustomerRequest.withdraw(customer, 100d));

        assertThat(customer.getBalance(), is(0d));
    }

    @Test(expected = OverdrawException.class)
    public void should_not_withdraw_money_when_balance_is_overdraw() {
        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 100d));
        bank.handleRequest(CustomerRequest.withdraw(customer, 200d));
    }

    @Test(expected = NotExistCustomerException.class)
    public void should_not_withdraw_or_deposit_money_when_customer_is_not_exist() {
        bank.handleRequest(CustomerRequest.withdraw(customer, 100d));

        bank.handleRequest(CustomerRequest.deposit(customer, 100d));
    }

    @Test
    public void should_use_sendMessage_when_addCustomer() {
        bank.addCustomer(customer);

        verify(emailSender).sendMessage("syyan123@thebank.com", "Dear syyan123, Welcome to the Bank");
    }

    @Test
    public void should_use_sendMessage_when_customer_balance_is_over_40000() {
        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 40000d));

        verify(emailSender).sendMessage("manager@thebank.com", "syyan123 is now a premium customer");
    }

    @Test
    public void should_use_sendMessage_only_once_when_customer_is_deposit_or_withdraw() {
        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 40000d));
        bank.handleRequest(CustomerRequest.deposit(customer, 10000d));
        bank.handleRequest(CustomerRequest.withdraw(customer, 50000d));
        bank.handleRequest(CustomerRequest.deposit(customer, 40000d));

        verify(emailSender, times(1)).sendMessage("manager@thebank.com", "syyan123 is now a premium customer");
    }

    @Test
    public void should_not_use_sendMessage_only_once_when_customer_is_not_over_40000() {
        bank.addCustomer(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 10000d));

        verify(emailSender, never()).sendMessage("manager@thebank.com", "syyan123 is now a premium customer");
    }

    @Test
    public void should_set_dateOfJoin_when_customer_is_added() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        bank.addCustomer(customer);

        assertThat(customer.getDateOfJoin().get(Calendar.DATE), is(calendar.get(Calendar.DATE)));
    }
}
