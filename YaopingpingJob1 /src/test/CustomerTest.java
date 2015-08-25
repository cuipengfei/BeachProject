import entity.Account;
import entity.Customer;
import exception.AccountNameRepeatedException;
import exception.OverdrawException;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void should_create_customer_with_valid_initial_account_with_balance_0_given_valid_name() {
        Customer customer = new Customer("yaoping123", Calendar.getInstance());

        assertThat(customer.findAccountByName("current").getBalance(), is(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_create_a_customer_if_nickname_contains_uppercase() throws Exception {
        new Customer("UPPERCASE", Calendar.getInstance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_create_a_customer_if_nickname_contains_special_characters() throws Exception {
        new Customer("@!@@#", Calendar.getInstance());
    }

    @Test
    public void should_create_account_when_account_name_is_not_repeated() throws AccountNameRepeatedException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        assertNotNull(customer.createAccount("account1"));
    }

    @Test(expected = AccountNameRepeatedException.class)
    public void should_reject_create_account_when_repeated() throws AccountNameRepeatedException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        customer.createAccount("current");
    }

    @Test
    public void should_find_Account_by_account_name() throws AccountNameRepeatedException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        customer.createAccount("liping");

        customer.createAccount("yaoyao");

        Account account = customer.findAccountByName("yaoyao");

        assertThat(account.getAccountName(), is("yaoyao"));
    }

    @Test
    public void should_calculate_the_total_assets() throws AccountNameRepeatedException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        Account account = customer.createAccount("liping");

        account.addBalance(100d);

        Account account1 = customer.createAccount("yaoyao");

        account1.addBalance(200d);

        account1.minusBalance(100d);

        assertThat(customer.calculateTotalAssets(), is(200d));
    }

    @Test
    public void should_transfer_successfully_when_an_account_balance_more_than_the_transfer_amount() throws OverdrawException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        Account transferAccount = new Account("transferAccount");

        transferAccount.addBalance(500d);

        Account receiveAccount = new Account("receiveAccount");

        customer.transferAccount(transferAccount, receiveAccount, 450d);

        assertThat(transferAccount.getBalance(), is(50d));

        assertThat(receiveAccount.getBalance(), is(450d));
    }
}