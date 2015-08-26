package beach.tw.handlers;

import beach.tw.entity.Account;
import beach.tw.entity.Customer;
import beach.tw.exception.InsufficientException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static beach.tw.requests.CustomerRequest.deposit;
import static beach.tw.requests.CustomerRequest.transfer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mlding on 8/25/15.
 */
public class TransferHandlerTest {
    private DepositHandler depositHandler;
    private TransferHandler transferHandler;
    private Customer customer;

    @Before
    public void setUp() {
        depositHandler = new DepositHandler();
        transferHandler = new TransferHandler();
        customer = Customer.createCustomer("mike", new Date());
        customer.setJoiningDate(Calendar.getInstance());
    }

    @Test
    public void should_transfer_money() throws Exception {
        Account first = new Account("first");
        customer.addAccount(first);

        depositHandler.handle(deposit(customer, 800, "current"));
        transferHandler.handle(transfer(customer, customer.getAccount("current"), customer.getAccount("first"), 200));

        assertThat(customer.getAccount("current").getMoney(), is(600));
        assertThat(customer.getAccount("first").getMoney(), is(200));
    }

    @Test(expected = InsufficientException.class)
    public void should_not_transfer_money_over_current_money_even_can_overdraft() throws Exception {
        customer.getAccount("current").setIsOverdraft(true);
        customer.getAccount("current").setLimit(1000);
        Account first = new Account("first");
        customer.addAccount(first);

        depositHandler.handle(deposit(customer, 800, "current"));
        transferHandler.handle(transfer(customer, customer.getAccount("current"), customer.getAccount("first"), 1200));
    }
}