import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yuzhang on 8/12/15.
 */
public class TestCustomer {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void should_add_successful_when_nickname_is_valid_and_norepeate() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank();
        assertThat("add successful", is(bank.addToBank(customer1)));
    }

    @Test
    public void should_balance_equals_300_when_deposit_300() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank();
        bank.addToBank(customer1);

        customer1.myAccount = new Account(customer1);
        assertThat(300,is(customer1.myAccount.deposit(300)));
    }

    @Test
    public void should_balance_equals_200_when_withdraw_100() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank();
        bank.addToBank(customer1);

        customer1.myAccount = new Account(customer1);
        customer1.myAccount.deposit(300);
        assertThat(200,is(customer1.myAccount.withdraw(100)));

    }

    @Test
    public void should_throw_exception_when_overdraw() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank();
        bank.addToBank(customer1);

        customer1.myAccount = new Account(customer1);
        customer1.myAccount.deposit(300);

        try {
            customer1.myAccount.withdraw(301);
        } catch (Exception e) {
            assertThat(e.getMessage(),is("overdraw"));
        }
    }
}
