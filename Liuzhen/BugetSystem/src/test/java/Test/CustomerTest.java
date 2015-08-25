package test;

import customer.Customer;
import myException.AccountNameNotUniqueException;
import myException.OverTransferException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by zhenliu on 8/25/15.
 */
public class CustomerTest {
    public Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = Customer.createCustomer("liuzhen11", Calendar.getInstance());
        customer.addAccount("account1");
    }

    @Test
    public void should_have_a_default_account_when_a_customer_is_created() throws Exception {
        assertNotNull(customer.getAccount("current"));
    }

    @Test(expected = AccountNameNotUniqueException.class)
    public void should_throw_exception_when_a_customer_add_a_account_which_has_same_name_with_existed_account() throws Exception {
        customer.addAccount("account1");
    }

    @Test
    public void should_return_the_total_balance_when_a_customer_want_to_know() throws Exception {
        customer.addAccount("account2");

        customer.getAccount("current").setBalance(400.0);
        customer.getAccount("account1").setBalance(1000.0);
        customer.getAccount("account2").setBalance(3000.0);

        assertThat(customer.getTotalBalance(), is(4400.0));
    }

    @Test
    public void should_transfer_successfully_when_an_account_transfer_money_less_than_its_balance_to_another_account() throws Exception {
        customer.getAccount("current").setBalance(4000.0);
        customer.transfer("current", "account1", 1000.0);

        assertThat(customer.getAccount("current").getBalance(), is(3000.0));
        assertThat(customer.getAccount("account1").getBalance(),is(1000.0));
    }

    @Test(expected = OverTransferException.class)
    public void should_throw_exception_when_an_account_transfer_money_more_than_its_balance_to_another_account() throws Exception {
        customer.getAccount("current").setBalance(4000.0);
        customer.transfer("current", "account1", 5000.0);
    }

    @Test(expected = OverTransferException.class)
    public void should_throw_exception_when_an_account_with_overdraft_facility_transfer_money_more_than_its_balance_to_another_account() throws Exception {
        customer.getAccount("current").setBalance(4000.0);
        customer.getAccount("current").setOverdraftAllowed(true);

        customer.transfer("current", "account1", 5000.0);
    }
}
