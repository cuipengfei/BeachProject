package entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    public Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
    }

    @Test
    public void should_has_initial_account_with_balance_0() {
        assertThat(account.getBalance(), is(0.0));
    }

    @Test
    public void should_add_to_account() {
        account.addBalance(20.0);
        assertThat(account.getBalance(), is(20.0));
    }

    @Test
    public void should_minus_to_account() {
        account.addBalance(20.0);
        account.minusBalance(10.0);
        assertThat(account.getBalance(), is(10.0));
    }

}