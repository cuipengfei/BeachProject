package main.java.com.thoughtworks;

import main.java.com.thoughtworks.exception.OverdrawException;
import org.junit.Test;

import java.util.Date;

import static main.java.com.thoughtworks.requests.CustomerRequest.deposit;
import static main.java.com.thoughtworks.requests.CustomerRequest.withdraw;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BankTest {


    private EmailSender emailSender = mock(EmailSender.class);

    private Bank bank = new Bank(emailSender);

    @Test
    public void should_add_success_when_give_the_valid_information() throws Exception {
        Customer customer = new Customer("yan123", new Date());
        assertTrue(bank.addCustomer(customer));
    }

    @Test
    public void should_add_failure_when_give_the_valid_nickName() throws Exception {
        Customer notValidCustomer = new Customer("YAN", new Date());
        assertFalse(bank.addCustomer(notValidCustomer));
    }

    @Test
    public void should_add_failure_when_give_the_same_nickName() throws Exception {
        Customer customer = new Customer("yan123", new Date());
        assertTrue(bank.addCustomer(customer));
        Customer existCustomer = new Customer("yan123", new Date());
        assertFalse(bank.addCustomer(existCustomer));
    }

    @Test
    public void should_deposit_money_when_customer_is_valid() throws Exception, OverdrawException {
        Customer syyan = new Customer("syyan123", new Date());

        bank.addCustomer(syyan);
        bank.handleRequest(deposit(syyan, 100d));
        assertThat(syyan.getBalance(), is(100d));
    }

    @Test
    public void should_withdraw_money_when_balance_is_not_overdraw() throws Exception, OverdrawException {
        Customer syyan = new Customer("syyan123", new Date());

        bank.addCustomer(syyan);
        bank.handleRequest(deposit(syyan, 100d));
        bank.handleRequest(withdraw(syyan, 100d));
        assertThat(syyan.getBalance(), is(0d));
    }

    @Test(expected = OverdrawException.class)
    public void should_not_withdraw_money_when_balance_is_overdraw() throws Exception, OverdrawException {
        Customer syyan = new Customer("syyan123", new Date());

        bank.addCustomer(syyan);
        bank.handleRequest(deposit(syyan, 100d));
        bank.handleRequest(withdraw(syyan, 200d));
    }

    @Test
    public void should_not_withdraw_or_deposit_money_when_customer_is_not_exist() throws Exception, OverdrawException {
        Customer unexist = new Customer("unexist", new Date());

        bank.handleRequest(withdraw(unexist, 100d));
        assertThat(unexist.getBalance(), is(0d));

        bank.handleRequest(deposit(unexist, 100));
        assertThat(unexist.getBalance(), is(0d));
    }


    @Test
    public void should_return_true_when_send_email_success() {
        Customer syyan = new Customer("syyan", new Date());
        bank.addCustomer(syyan);
        verify(emailSender).sendMessage("syyan@thebank.com", "Dear syyan, Welcome to the Bank");
    }

}
