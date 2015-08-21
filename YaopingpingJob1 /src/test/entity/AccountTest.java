package entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ppyao on 8/21/15.
 */
public class AccountTest {
    public Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
    }

    @Test
    public void shouldHasInitialAccountWithBalance0() {
        assertThat(account.getBalance(), is(0.0));
    }

    @Test
    public void shouldAddBalanceToAccount() {
        account.addBalance(20.0);
        assertThat(account.getBalance(), is(20.0));
    }

    @Test
    public void shouldMinusBalanceToAccount() {
        account.addBalance(20.0);
        account.minusBalance(10.0);
        assertThat(account.getBalance(), is(10.0));
    }

}