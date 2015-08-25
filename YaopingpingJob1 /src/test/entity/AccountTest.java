package entity;

import exception.OverdrawException;
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
    public void should_has_initial_account_with_name_current() {
        assertThat(account.getAccountName(), is("current"));
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

    @Test
    public void should_transfer_successfully_after_transfers_account_balance_greater_than_0() throws OverdrawException {
        Account account=new Account("transferAccount");

        Account account1=new Account("receiveAccount");

        account.addBalance(100d);

        account.transferBalance(account1, 50d);

        assertThat(account.getBalance(), is(50d));

        assertThat(account1.getBalance(),is(50d));
    }

    @Test(expected = OverdrawException.class)
    public void should_throw_exception_after_transfers_account_balance_below_0_even_if_allowed_overdraft() throws OverdrawException {
        Account account = new Account("transferAccount");

        account.setOverdraftAllowed(true);

        Account account1 = new Account("receiveAccount");

        account.transferBalance(account1, 50d);
    }

}