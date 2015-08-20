package Test;

import Bank.Bank;
import Customer.Customer;
import MailSender.FasterMailSender;
import MailSender.MailSender;
import MailSender.StandardMailSender;
import MyException.CustomerNotExistException;
import MyException.OverdrawException;
import Request.CustomerRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
public class BankTest {
    public Bank bank0, bank1;
    public Customer customer;
    public MailSender mockSender;

    @Before
    public void setUp() throws Exception {
        bank0 = new Bank(new StandardMailSender());
        customer = Customer.createCustomer("liuzhen11", Calendar.getInstance());
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
        Customer customer = Customer.createCustomer("liuzhen11", Calendar.getInstance());

        bank1.add(customer);

        verify(mockSender).sendEmail("liuzhen11@thebank.com", "Dear liuzhen11, Welcome to the Bank!");
    }

    @Test
    public void should_send_manager_an_email_when_a_customer_becomes_a_premium_customer_from_a_ordinary_customer() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11", Calendar.getInstance());

        bank1.add(customer);
        bank1.handleRequest(CustomerRequest.deposit(customer, 40000.0));

        verify(mockSender).sendEmail("manager@thebank.com",customer+" is now a premium customer.");
    }

    @Test
    public void should_not_send_manager_an_email_when_a_customer_have_been_a_premium_customer_yet() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11", Calendar.getInstance());

        bank1.add(customer);
        bank1.handleRequest(CustomerRequest.deposit(customer, 40000.0));
        bank1.handleRequest(CustomerRequest.deposit(customer, 10000.0));
        bank1.handleRequest(CustomerRequest.withDraw(customer, 50000.0));
        bank1.handleRequest(CustomerRequest.deposit(customer, 40000.0));

        verify(mockSender, times(1)).sendEmail("manager@thebank.com",customer+" is now a premium customer.");

    }

    @Test
    public void should_not_send_manager_an_email_when_a_customer_doesnot_meet_the_condition_of_been_premium() throws Exception {
        Customer customer = Customer.createCustomer("liuzhen11", Calendar.getInstance());

        bank1.add(customer);
        bank1.handleRequest(CustomerRequest.deposit(customer, 100.0));

        verify(mockSender, never()).sendEmail("manager@thebank.com",customer+" is now a premium customer.");

    }

    @Test
    public void should_add_joining_date_when_a_customer_be_added_successfully() throws Exception {
        bank1.add(customer);

        assertEquals(customer.getJoiningDate(), Calendar.getInstance());
    }

    @Test
    public void should_get_bonus_when_a_customer_has_been_added_for_two_years() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        Customer customer1 = Customer.createCustomer("jeanliu",Calendar.getInstance());

        bank1.add(customer1);
        customer1.setJoiningDate(calendar);
        bank1.handleRequest(CustomerRequest.deposit(customer1, 100.0));
        bank1.handleRequest(CustomerRequest.deposit(customer1, 100.0));

        assertThat(customer1.getTwoYearsBonus(),is(5.0));
        assertThat(customer1.getAccount(),is(205.0));
    }
}