package main.java.com.thoughtworks;

import java.util.Date;

import static main.java.com.thoughtworks.RequestType.Deposit;
import static main.java.com.thoughtworks.RequestType.Withdraw;
import static org.junit.Assert.*;

import main.java.com.thoughtworks.exception.OverdrawException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;


public class BankTest {

    @Test
    public void should_add_success_when_give_the_valid_information() throws Exception {

        Bank bank = new Bank();
        Customer customer = new Customer("yan123", new Date());
        assertTrue(bank.addCustomer(customer));
    }

    @Test
    public void should_add_failure_when_give_the_valid_nickName() throws Exception {

        Bank bank = new Bank();
        Customer notValidCustomer = new Customer("YAN", new Date());
        assertFalse(bank.addCustomer(notValidCustomer));
    }

    @Test
    public void should_add_failure_when_give_the_same_nickName() throws Exception {

        Bank bank = new Bank();
        Customer customer = new Customer("yan123", new Date());
        assertTrue(bank.addCustomer(customer));
        Customer existCustomer = new Customer("yan123", new Date());
        assertFalse(bank.addCustomer(existCustomer));
    }

    @Test
    public void should_deposit_money_when_customer_is_valid() throws Exception, OverdrawException {
        Customer syyan = new Customer("syyan123", new Date());
        Bank bank = new Bank();
        bank.addCustomer(syyan);
        assertThat(bank.handle(syyan, Deposit,100d), is(100d));
    }

    @Test
    public void should_withdraw_money_when_balance_is_not_overdraw() throws Exception, OverdrawException {
        Customer syyan = new Customer("syyan123", new Date());
        Bank bank = new Bank();
        bank.addCustomer(syyan);
        bank.handle(syyan, Deposit, 100d);
        assertThat(bank.handle(syyan, Withdraw, 100d), is(0d));
    }

    @Test(expected = OverdrawException.class)
    public void should_not_withdraw_money_when_balance_is_overdraw() throws Exception, OverdrawException {
        Customer syyan = new Customer("syyan123", new Date());
        Bank bank = new Bank();
        bank.addCustomer(syyan);
        bank.handle(syyan, Deposit,100d);
        bank.handle(syyan, Withdraw, 200d);
    }
    @Test
    public void should_not_withdraw_money_when_customer_is_not_exist() throws Exception, OverdrawException {
        Customer unexist = new Customer("unexist", new Date());
        Bank bank = new Bank();

        assertThat(bank.handle(unexist, Withdraw, 100d), is(0d));
    }
    @Test
    public void should_not_deposit_money_when_customer_is_not_exist() throws Exception, OverdrawException {
        Customer unexist = new Customer("unexist", new Date());
        Bank bank = new Bank();

        assertThat(bank.handle(unexist, Deposit,100d), is(0d));
    }

}
