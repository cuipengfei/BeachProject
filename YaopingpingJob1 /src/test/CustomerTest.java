import entity.Account;
import entity.Customer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void should_create_customer_with_valid_initial_account_with_balance_0_given_valid_name() {
        //when
        Customer customer = new Customer("yaoping123", Calendar.getInstance());

        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));
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
    public void should_create_account_when_account_name_is_not_repeated() {
        Customer customer = new Customer("yaoping", Calendar.getInstance());
        assertNotNull(customer.createAccount("account1"));

    }

    @Test
    public void should_reject_create_account_when_repeated() {
        Customer customer = new Customer("yaoping", Calendar.getInstance());
        assertNull(customer.createAccount("current"));

    }


    @Test
    public void should_calculate_the_accounts_balances() {
        Customer customer = new Customer("yaoping", Calendar.getInstance());
        List<Account> accounts = new ArrayList<Account>();
        Account account = new Account();
        account.addBalance(100d);
        Account account1 = new Account();
        account1.addBalance(200d);
        accounts.add(account);
        accounts.add(account1);
        double totalBalance = customer.calculate();

        assertThat(totalBalance, is(300d));


    }
}