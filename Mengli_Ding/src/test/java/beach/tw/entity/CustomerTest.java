package beach.tw.entity;

import beach.tw.handlers.DepositHandler;
import beach.tw.handlers.WithdrawHandler;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static beach.tw.entity.Customer.printAccountInfo;
import static beach.tw.requests.CustomerRequest.deposit;
import static beach.tw.requests.CustomerRequest.withdraw;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mlding on 8/15/15.
 */
public class CustomerTest {
    @Test
    public void should_create_instanceOf_customer() throws Exception {
        Customer mike1 = Customer.createCustomer("mike1", new Date());
        assertThat(mike1.getName(), is("mike1"));
    }

    @Test
    public void should_not_create_instanceOf_cCustomer_when_name_invalid() throws Exception {
        Customer mike1 = Customer.createCustomer("mike@#", new Date());
        assertThat(mike1, is(Customer.invalidCustomer()));
    }

    @Test
    public void should_have_empty_account_by_default() throws Exception {
        Customer customer = Customer.createCustomer("mike", new Date());
        assertThat(customer.getAccount("current").getMoney(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_reject_adding_for_null_account() throws Exception {
        Customer customer = Customer.createCustomer("mike", new Date());
        customer.addAccount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_reject_adding_for_invalid_account() throws Exception {
        Customer customer = Customer.createCustomer("mike", new Date());
        customer.addAccount(Account.invalidAccount());
    }

    @Test
    public void should_print_all_account() throws Exception {
        DepositHandler depositHandler = new DepositHandler();
        WithdrawHandler withdrawHandler = new WithdrawHandler();

        Customer customer = Customer.createCustomer("ding", new Date());
        customer.setJoiningDate(Calendar.getInstance());

        customer.addAccount(new Account("current"));
        customer.addAccount(new Account("savings"));
        customer.getAccount("current").setIsOverdraft(true);
        customer.getAccount("current").setLimit(1000);

        depositHandler.handle(deposit(customer, 900, "current"));
        withdrawHandler.handle(withdraw(customer, 1500, "current"));
        depositHandler.handle(deposit(customer, 300, "savings"));

        printAccountInfo(customer);
    }
}