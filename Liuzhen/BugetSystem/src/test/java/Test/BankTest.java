package Test;

import Bank.*;
import Customer.*;
import MailSender.FasterMailSender;
import MailSender.*;
import MyException.CustomerNotExistException;
import MyException.OverdrawException;
import Request.CustomerRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
public class BankTest {
    Bank bank0;
    Customer customer;
    MailSender mockSender;
    Bank bank1;
    @Before
    public void setUp() throws Exception {
        bank0 = new Bank(new StandardMailSender());
        customer = Customer.createCustomer("liuzhen11", new Date());
        mockSender = Mockito.mock(FasterMailSender.class);
        bank1 = new Bank(mockSender);

    }

    @Test
    public void should_get_a_blank_account_when_a_customer_be_added_in_a_bank() throws Exception {
        bank0.add(customer);

        assertThat(customer.getAccount(), is(0.0));
    }

    @Test
    public void should_deposit_successfully_when_some_money_be_deposited_in_account() throws Exception {
        bank0.add(customer);

        bank0.handleRequest(CustomerRequest.deposit(customer, 1000.0));

        assertThat(customer.getAccount(), is(1000.0));

    }

    @Test
    public void should_withdraw_successfully_when_withdraw_method_be_used() throws Exception {
        bank0.add(customer);

        bank0.handleRequest(CustomerRequest.deposit(customer, 1000.0));
        bank0.handleRequest(CustomerRequest.withDraw(customer, 900.0));

        assertThat(customer.getAccount(), is(100.0));
    }

    @Test(expected = OverdrawException.class)
    public void should_throws_exception_when_an_account_be_withdraw_money_more_than_its_current_money() throws Exception {
        bank0.add(customer);

        bank0.handleRequest(CustomerRequest.deposit(customer, 1000.0));
        bank0.handleRequest(CustomerRequest.withDraw(customer, 1100.0));
    }

    @Test(expected = CustomerNotExistException.class)
    public void should_throws_exception_when_a_customer_does_not_exist() throws Exception {
        bank0.handleRequest(CustomerRequest.deposit(customer, 1000.0));
    }

    @Test
    public void should_call_sendEmail_method_successfully_when_a_customer_be_add_into_the_bank() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank1.add(customer);

        verify(mockSender).sendEmail("liuzhen11@thebank.com", "Dear liuzhen11, Welcome to the Bank!");
    }

    @Test
    public void should_send_manager_an_email_when_a_customer_becomes_a_premium_customer_from_a_ordinary_customer() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank1.add(customer);
        bank1.handleRequest(CustomerRequest.deposit(customer, 40000.0));

        verify(mockSender).sendEmail("manager@thebank.com",customer+" is now a premium customer.");
    }

    @Test
    public void should_not_send_manager_an_email_when_a_customer_have_been_a_premium_customer_yet() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank1.add(customer);
        bank1.handleRequest(CustomerRequest.deposit(customer, 40000.0));
        bank1.handleRequest(CustomerRequest.deposit(customer, 10000.0));
        bank1.handleRequest(CustomerRequest.withDraw(customer, 50000.0));
        bank1.handleRequest(CustomerRequest.deposit(customer, 40000.0));

        verify(mockSender, times(1)).sendEmail("manager@thebank.com",customer+" is now a premium customer.");

    }

    @Test
    public void should_not_send_manager_an_email_when_a_customer_doesnot_meet_the_condition_of_been_premium() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank1.add(customer);
        bank1.handleRequest(CustomerRequest.deposit(customer, 100.0));

        verify(mockSender, never()).sendEmail("manager@thebank.com",customer+" is now a premium customer.");

    }

    @Test
    public void should_add_joining_date_when_a_customer_be_added_successfully() throws Exception {
        bank0.add(customer);

        assertNotNull(customer.getJoiningDate());
    }
}