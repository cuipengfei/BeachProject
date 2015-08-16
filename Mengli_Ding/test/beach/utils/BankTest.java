package beach.utils;

import beach.utils.requests.InsufficientException;
import org.junit.Test;

import java.util.Date;

import static beach.utils.requests.CustomerRequest.deposit;
import static beach.utils.requests.CustomerRequest.withdraw;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by mlding on 8/15/15.
 */
public class BankTest {
    @Test
    public void BankShouldAddValidCustomer() throws Exception {
        //given
        Customer miya11 = Customer.createCustomer("miya11", new Date());

        //when
        boolean isSuccessful = new Bank().addCustomer(miya11);

        //then
        assertTrue(isSuccessful);
    }

    @Test
    public void BankShouldNotAddInvalidCustomer() throws Exception {
        //given
        Customer Miya = Customer.createCustomer("Miya", new Date());

        //when
        boolean isSuccessful = new Bank().addCustomer(Miya);

        //then
        assertFalse(isSuccessful);
    }

    @Test
    public void BankShouldNotAddSameNameCustomer() throws Exception {
        //given
        Bank bank = new Bank();
        Customer miya1 = Customer.createCustomer("miya", new Date());
        Customer miya2 = Customer.createCustomer("miya", new Date());

        //when
        boolean isSuccessful1 = bank.addCustomer(miya1);

        //then
        assertTrue(isSuccessful1);

        //when
        boolean isSuccessful2 = bank.addCustomer(miya2);

        //then
        assertFalse(isSuccessful2);
    }

    @Test
    public void shouldBeAbleToDepositMoney() throws Exception {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        Bank bank = new Bank();
        bank.addCustomer(mike);

        //when
        bank.handleRequest(deposit(mike, 8));

        //then
        assertThat(mike.getAccount().getMoney(), is(8));
    }

    @Test
    public void shouldBeAbleToWithdrawMoney() throws Exception {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        Bank bank = new Bank();
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 8));

        //when
        bank.handleRequest(withdraw(mike, 2));

        //then
        assertThat(mike.getAccount().getMoney(), is(6));
    }

    @Test(expected = InsufficientException.class)
    public void shouldNotBeAbleToWithdrawMoneyMoreThanBalance() throws Exception {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        Bank bank = new Bank();
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 8));

        //when
        bank.handleRequest(withdraw(mike, 10));

        //then
        //throw an exception
    }

    @Test
    public void shouldNotHandleAnyRequestIfCustomerWasNotAdded() throws Exception {
        //given customer not added
        Customer aaa = Customer.createCustomer("aaa", new Date());
        Bank bank = new Bank();

        //when
        bank.handleRequest(deposit(aaa, 8));

        //then
        assertThat(aaa.getAccount().getMoney(), is(0));
    }

    @Test
    public void shouldSentMessageIfCustomerWasAdded() throws Exception {
        Customer bbb = Customer.createCustomer("bbb", new Date());
        Bank bank = new Bank();
        bank.addCustomer(bbb);

        assertThat(bbb.getEmail().getContent(), is("Dear " + bbb.getName() + ", Welcome to the Bank"));
    }

    @Test
    public void shouldNotSentMessageIfCustomerWasNotAdded() throws Exception {
        Customer bbb = Customer.createCustomer("bbb", new Date());

        assertNull(bbb.getEmail().getContent());
    }
}