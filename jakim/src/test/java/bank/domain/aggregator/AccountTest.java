package bank.domain.aggregator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AccountTest {
    @Test
    public void should_has_initial_account_with_balance_0() throws Exception {
        Account account = new Account();
        assertThat(account.balance(), is(0f));
    }

    @Test
    public void should_add_to_account() throws Exception {
        Account account = new Account();
        assertThat(account.balance(), is(0f));

        float balance = account.add(30f);
        assertThat(balance, is(30f));
        assertThat(account.balance(), is(30f));

        float newBalance = account.add(3f);
        assertThat(newBalance, is(33f));
        assertThat(account.balance(), is(33f));
    }

    @Test
    public void should_minus_from_account() throws Exception {
        Account account = new Account();
        assertThat(account.balance(), is(0f));

        float balance = account.add(30f);
        assertThat(balance, is(30f));
        assertThat(account.balance(), is(30f));

        float newBalance = account.minus(3f);
        assertThat(newBalance, is(27f));
        assertThat(account.balance(), is(27f));
    }
}