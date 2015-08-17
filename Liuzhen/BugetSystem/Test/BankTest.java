import MyException.CustomerNotExistException;
import MyException.OverdrawException;
import Request.CustomerRequest;
import Src.MailSender;
import org.junit.Test;
import Src.Bank;
import Src.Customer;
import org.mockito.Mockito;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


import static org.mockito.Mockito.*;
public class BankTest {

    @Test
    public void should_get_a_blank_account_when_a_customer_be_added_in_a_bank() throws Exception {
        Bank bank = new Bank(new MailSender());
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.add(customer);

        assertThat(customer.getAccount(), is(0.0));
    }

    @Test
    public void should_deposit_successfully_when_some_money_be_deposited_in_account() throws Exception {
        Bank bank = new Bank(new MailSender());
        Customer customer = Customer.createCustomer("liuzhen11",new Date());
        bank.add(customer);

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));

        assertThat(customer.getAccount(), is(1000.0));

    }

    @Test
    public void should_withdraw_successfully_when_withdrawAll_method_be_used() throws Exception {
        Bank bank = new Bank(new MailSender());
        Customer customer = Customer.createCustomer("liuzhen11",new Date());
        bank.add(customer);

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));
        bank.handleRequest(CustomerRequest.withDraw(customer, 900.0));

        assertThat(customer.getAccount(), is(100.0));
    }

    @Test(expected = OverdrawException.class)
    public void should_throws_exception_when_an_account_be_withdraw_money_more_than_its_current_money() throws Exception {
        Bank bank = new Bank(new MailSender());
        Customer customer = Customer.createCustomer("liuzhen11",new Date());
        bank.add(customer);

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));
        bank.handleRequest(CustomerRequest.withDraw(customer, 1100.0));
    }

    @Test(expected = CustomerNotExistException.class)
    public void should_throws_exception_when_a_customer_does_not_exist() throws Exception {
        Bank bank = new Bank(new MailSender());
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));
    }

    @Test
    public void should_call_sendEmail_method_successfully_when_a_customer_be_add_into_the_bank() throws Exception {
        MailSender mockSender = Mockito.mock(MailSender.class);
        Bank bank = new Bank(mockSender);
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.add(customer);

        verify(mockSender).sendEmail("thebank@thebank.com", "liuzhen11@thebank.com", "Welcome Message", "Dear liuzhen11, Welcome to the Bank!");
    }

    @Test
    public void should_send_manager_an_email_when_a_customer_becomes_a_premium_customer_from_a_ordinary_customer() throws Exception {
        MailSender mockSender = Mockito.mock(MailSender.class);
        Bank bank = new Bank(mockSender);
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.add(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 40000.0));

        verify(mockSender).sendEmail("thebank@thebank.com","manager@thebank.com","new premium customer",customer+" is now a premium customer.");
    }

    @Test
    public void should_not_send_manager_an_email_when_a_customer_have_been_a_premium_customer_yet() throws Exception {
        MailSender mockSender = Mockito.mock(MailSender.class);
        Bank bank = new Bank(mockSender);
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.add(customer);
        bank.handleRequest(CustomerRequest.deposit(customer, 40000.0));
        bank.handleRequest(CustomerRequest.deposit(customer, 10000.0));
        bank.handleRequest(CustomerRequest.withDraw(customer, 50000.0));
        bank.handleRequest(CustomerRequest.deposit(customer, 40000.0));

        verify(mockSender, times(1)).sendEmail("thebank@thebank.com","manager@thebank.com","new premium customer",customer+" is now a premium customer.");

    }
}