package beach.tw.handlers;

import beach.tw.entity.Customer;
import beach.tw.exception.InsufficientException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static beach.tw.requests.CustomerRequest.deposit;
import static beach.tw.requests.CustomerRequest.withdraw;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mlding on 8/25/15.
 */
public class WithdrawHandlerTest {
    private WithdrawHandler withdrawHandler;
    private DepositHandler depositHandler;
    private Customer customer;

    @Before
    public void setUp() {
        withdrawHandler = new WithdrawHandler();
        depositHandler = new DepositHandler();
        customer = Customer.createCustomer("mike", new Date());
        customer.setJoiningDate(Calendar.getInstance());
    }

    @Test
    public void should_withdraw_money() throws Exception {
        depositHandler.handle(deposit(customer, 18, "current"));
        withdrawHandler.handle(withdraw(customer, 2, "current"));

        assertThat(customer.getAccount("current").getMoney(), is(16));
    }


    @Test(expected = InsufficientException.class)
    public void should_not_withdraw_money_more_than_balance() {
        depositHandler.handle(deposit(customer, 50, "current"));
        withdrawHandler.handle(withdraw(customer, 60, "current"));
    }

    @Test
    public void should_overdraft_when_marked() throws Exception {
        customer.getAccount("current").setIsOverdraft(true);
        customer.getAccount("current").setLimit(1000);

        depositHandler.handle(deposit(customer, 200, "current"));
        withdrawHandler.handle(withdraw(customer, 300, "current"));
        withdrawHandler.handle(withdraw(customer, 900, "current"));

        assertThat(customer.getAccount("current").getMoney(), is(-1000));
    }

    @Test(expected = InsufficientException.class)
    public void should_not_continue_overdraft_when_remove_marked() throws Exception {
        customer.getAccount("current").setIsOverdraft(true);
        customer.getAccount("current").setLimit(1000);

        depositHandler.handle(deposit(customer, 200, "current"));
        withdrawHandler.handle(withdraw(customer, 300, "current"));

        assertThat(customer.getAccount("current").getMoney(), is(-100));

        customer.getAccount("current").setIsOverdraft(false);

        withdrawHandler.handle(withdraw(customer, 300, "current"));
    }

    @Test(expected = InsufficientException.class)
    public void should_not_overdraft_when_not_marked() throws Exception {
        depositHandler.handle(deposit(customer, 200, "current"));
        withdrawHandler.handle(withdraw(customer, 300, "current"));
    }
}