package test;

import customer.Customer;
import myException.AccountNameNotUniqueException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
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

    @Test(expected = AccountNameNotUniqueException.class)
    public void should_throw_exception_when_a_customer_add_a_account_which_has_same_name_with_existed_account() throws Exception {
        customer.addAccount("account1");
    }

    @Test
    public void should_return_the_total_balance_when_a_customer_want_to_know() throws Exception {
        customer.addAccount("account2");

        customer.getAccount("account1").setBalance(1000.0);
        customer.getAccount("account2").setBalance(3000.0);

        assertThat(customer.getTotalBalance(),is(4000.0));
    }
}
