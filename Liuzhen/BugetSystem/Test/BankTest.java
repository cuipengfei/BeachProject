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

/**
 * Created by zhenliu on 8/13/15.
 */
import static org.mockito.Mockito.*;
public class BankTest {
    @Test
    public void should_get_a_blank_account_when_a_customer_be_added_in_a_bank() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.add(customer);

        assertThat(customer.getAccount(), is(0.0));
    }

    @Test
    public void should_deposit_successfully_when_some_money_be_deposited_in_account() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("liuzhen11",new Date());
        bank.add(customer);

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));

        assertThat(customer.getAccount(), is(1000.0));

    }

    @Test
    public void should_withdraw_successfully_when_withdrawAll_method_be_used() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("liuzhen11",new Date());
        bank.add(customer);

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));
        bank.handleRequest(CustomerRequest.withDraw(customer, 900.0));

        assertThat(customer.getAccount(), is(100.0));
    }

    @Test(expected = OverdrawException.class)
    public void should_throws_exception_when_an_account_be_withdraw_money_more_than_its_current_money() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("liuzhen11",new Date());
        bank.add(customer);

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));
        bank.handleRequest(CustomerRequest.withDraw(customer, 1100.0));
    }

    @Test(expected = CustomerNotExistException.class)
    public void should_throws_exception_when_a_customer_does_not_exist() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.handleRequest(CustomerRequest.deposit(customer, 1000.0));
    }

  /*  @Test
    public void should_send_welcome_message_when_add_customer_successfully() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("arollalz",new Date());
        bank.add(customer);

        assertEquals(customer.getMessage(),"Dear" + customer.getNickName() + ", Welcome to the Bank!");
    }
  */
    @Test
    public void should_call_sendEmail_method_once_when_use_it() throws Exception {
        MailSender mockSender = Mockito.mock(MailSender.class);
        mockSender.sendEmail("thebankmanager@thebank.com", "user@thebank.com", "Welcome Message", "Dear user,Welcome to the Bank!");

        verify(mockSender).sendEmail("thebankmanager@thebank.com", "user@thebank.com", "Welcome Message", "Dear user,Welcome to the Bank!");
    }
}