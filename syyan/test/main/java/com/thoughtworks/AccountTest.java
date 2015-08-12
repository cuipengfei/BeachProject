package main.java.com.thoughtworks;

import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class AccountTest {

    @Test
    public void should_deposit_money_when_customer_is_valid() throws Exception {
        Account account = new Account();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer = new Customer("yan123", sdf.parse("2015-08-09"));
        assertThat(account.deposit(customer, 100),is(100));
    }
    @Test
    public void should_throw_exception_when_customer_is_invalid() throws Exception {
        Account account = new Account();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer = new Customer("YAN", sdf.parse("2015-08-09"));
        try {
            account.deposit(customer, 100);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(),is("Throw invalid account exception"));
        }
    }
    @Test
    public void should_withdraw_money_when_balance_is_not_overdraw() throws Exception {
        Account account = new Account();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer = new Customer("yan123", sdf.parse("2015-08-09"));
        account.deposit(customer, 100);
        assertThat(account.withdraw(customer, 100), is(0));
    }




}