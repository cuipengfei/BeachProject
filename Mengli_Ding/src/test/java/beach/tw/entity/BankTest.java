package beach.tw.entity;

import beach.tw.external.FasterMessageGateway;
import beach.tw.external.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static beach.tw.requests.CustomerRequest.deposit;
import static beach.tw.requests.CustomerRequest.withdraw;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Created by mlding on 8/15/15.
 */
public class BankTest {

    private Bank bank;
    private FasterMessageGateway messageGateway;

    @Before
    public void setUp() {
        messageGateway = mock(FasterMessageGateway.class);
        bank = new Bank(messageGateway);
    }

    @Test
    public void bank_should_add_valid_customer() throws Exception {
        Customer miya11 = Customer.createCustomer("miya11", new Date());
        FasterMessageGateway fasterMessageGateway2 = new FasterMessageGateway();

        boolean isSuccessful = new Bank(fasterMessageGateway2).addCustomer(miya11);

        assertTrue(isSuccessful);
    }

    @Test
    public void bank_should_not_add_invalid_customer() throws Exception {
        Customer Miya = Customer.createCustomer("Miya", new Date());

        boolean isSuccessful = bank.addCustomer(Miya);

        assertFalse(isSuccessful);
    }

    @Test
    public void bank_should_not_add_same_name_customer() throws Exception {
        Customer miya1 = Customer.createCustomer("miya", new Date());
        Customer miya2 = Customer.createCustomer("miya", new Date());

        boolean isSuccessful1 = bank.addCustomer(miya1);

        assertTrue(isSuccessful1);

        boolean isSuccessful2 = bank.addCustomer(miya2);

        assertFalse(isSuccessful2);
    }

    @Test
    public void should_sent_message_if_customer_was_added() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);

        verify(messageGateway).sendMail(anyString(), anyString());
    }

    @Test
    public void should_not_sent_message_if_customer_was_not_added() {
        Customer customer = Customer.createCustomer("ding", new Date());

        verify(messageGateway, never()).sendMail(anyString(), anyString());
    }

    @Test
    public void should_sent_message_to_manager_if_have_premium_customer() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 60000, "current"));

        verify(messageGateway).sendMail("manager@thebank.com", customer.getName() + " is now a premium customer");
    }

    @Test
    public void should_not_sent_message_to_manager_again_if_customer_is_premiun_once() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 60000, "current"));
        bank.handleRequest(deposit(customer, 10000, "current"));
        bank.handleRequest(withdraw(customer, 60000, "current"));

        verify(messageGateway, times(2)).sendMail(anyString(), anyString());
    }

    @Test
    public void should_set_joiningDate_if_customer_added_to_bank() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        bank.addCustomer(customer);

        assertThat(customer.getJoiningDate().get(Calendar.DAY_OF_MONTH), is(calendar.get(Calendar.DAY_OF_MONTH)));
    }

    @Test
    public void should_not_set_joiningDate_if_customer_not_added_to_bank() {
        Customer customer = Customer.createCustomer("ding", new Date());

        assertNull(customer.getJoiningDate());
    }

    @Test
    public void is_log_message_successful() throws Exception {
        when(messageGateway.sendMail(anyString(), anyString())).thenReturn(Status.OK);
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);

        verify(messageGateway).sendMail(anyString(), anyString());
        assertTrue(bank.isCalls());
    }
}