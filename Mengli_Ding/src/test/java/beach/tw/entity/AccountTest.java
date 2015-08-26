package beach.tw.entity;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mlding on 8/25/15.
 */
public class AccountTest {

    @Test
    public void account_should_has_an_initial_balance() throws Exception {
        Account account = new Account("accountName");

        assertThat(account.getMoney(), is(0));
    }

    @Test
    public void account_should_add_balance() throws Exception {
        Account account = new Account("mike");

        account.add(30);

        assertThat(account.getMoney(), is(30));
    }

    @Test
    public void account_should_minus_balance() throws Exception {
        Account account = new Account("mike");
        account.add(60);

        account.minus(30);

        assertThat(account.getMoney(), is(30));
    }

    @Test
    public void customer_should_has_default_account() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());

        assertNotNull(ding.getAccount("current"));
    }

    @Test
    public void should_not_create_same_name_account() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());
        ding.addAccount(new Account("current"));
        Account current = new Account("current");

        boolean isSuccessful = ding.addAccount(current);

        assertFalse(isSuccessful);
    }

    @Test
    public void should_create_valid_account() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());
        Account first = new Account("first");

        boolean isSuccessful = ding.addAccount(first);

        assertTrue(isSuccessful);
    }
}