import entity.Account;
import entity.Customer;
import exception.AccountNameRepeatedException;
import exception.AccountNotExistException;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CustomerTest {
    @Test
    public void should_create_customer_with_valid_initial_account_with_name_current_given_valid_name() {
        Customer customer = new Customer("yaoping123", Calendar.getInstance());

        assertThat(customer.getAccounts().get(0).getAccountName(), is("current"));
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
    public void should_create_account_when_account_name_is_not_repeated() throws AccountNameRepeatedException, AccountNotExistException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        assertNotNull(customer.openAccount("account1"));
    }

    @Test(expected = AccountNameRepeatedException.class)
    public void should_reject_create_account_when_repeated() throws AccountNameRepeatedException, AccountNotExistException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        customer.openAccount("current");
    }

    @Test
    public void should_find_Account_by_account_name() throws AccountNameRepeatedException, AccountNotExistException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        customer.openAccount("liping");

        customer.openAccount("yaoyao");

        Account account = customer.findAccountByName("yaoyao");

        assertThat(account.getAccountName(), is("yaoyao"));
    }

    @Test
    public void should_calculate_the_total_assets() throws AccountNameRepeatedException, AccountNotExistException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        Account account = customer.openAccount("liping");

        account.addBalance(100d);

        Account account1 = customer.openAccount("yaoyao");

        account1.addBalance(200d);

        account1.minusBalance(100d);

        assertThat(customer.calculateTotalAssets(), is(200d));
    }

    @Test
    public void should_display_all_accounts_statements() throws AccountNameRepeatedException, AccountNotExistException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        Account account = customer.openAccount("Savings");

        Account accountISA = customer.openAccount("ISA");

        customer.findAccountByName("current").addBalance(111d);

        account.minusBalance(100d);

        accountISA.addBalance(400d);

        customer.displayAllAccountsStatements();
    }
}