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

        assertThat(300, is(customer1.getMyAccount().deposit(300)));
    }

    @Test
    public void should_balance_equals_200_when_withdraw_100() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank();
        bank.addToBank(customer1);

        customer1.getMyAccount().deposit(300);
        assertThat(200,is(customer1.getMyAccount().withdraw(100)));

    }

    @Test(expected = Exception.class)
    public void should_throw_exception_when_overdraw() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank();
        bank.addToBank(customer1);

        customer1.getMyAccount().deposit(300);
        customer1.getMyAccount().withdraw(301);
        /*try {
            customer1.getMyAccount().withdraw(301);
        } catch (Exception e) {
            assertThat(e.getMessage(),is("overdraw"));
        }*/
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_the_no_added_customer_deposit() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        customer1.getMyAccount().deposit(300);
    }
}
