package beach.tw;

import beach.external.FasterMessageGateway;
import beach.tw.entity.Bank;
import beach.tw.entity.Customer;
import beach.tw.exception.InsufficientException;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static beach.tw.requests.CustomerRequest.deposit;
import static beach.tw.requests.CustomerRequest.withdraw;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Created by mlding on 8/15/15.
 */
public class BankTest {

    private Bank bank;
    private FasterMessageGateway mockedFasterMessageGateway;

    @Before
    public void setUp() {
        mockedFasterMessageGateway = mock(FasterMessageGateway.class);
        bank = new Bank(mockedFasterMessageGateway);
    }

    @Test
    public void BankShouldAddValidCustomer() throws ParseException {
        //given
        Customer miya11 = Customer.createCustomer("miya11", new Date());

        //when
        boolean isSuccessful = bank.addCustomer(miya11);

        //then
        assertTrue(isSuccessful);
    }

    @Test
    public void BankShouldNotAddInvalidCustomer() throws ParseException {
        //given
        Customer Miya = Customer.createCustomer("Miya", new Date());

        //when
        boolean isSuccessful = bank.addCustomer(Miya);

        //then
        assertFalse(isSuccessful);
    }

    @Test
    public void BankShouldNotAddSameNameCustomer() throws ParseException {
        //given
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
    public void shouldBeAbleToDepositMoney() throws ParseException {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);

        //when
        bank.handleRequest(deposit(mike, 8));

        //then
        assertThat(mike.getAccount().getMoney(), is(8));
    }

    @Test
    public void shouldBeAbleToWithdrawMoney() throws ParseException {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 8));

        //when
        bank.handleRequest(withdraw(mike, 2));

        //then
        assertThat(mike.getAccount().getMoney(), is(6));
    }

    @Test(expected = InsufficientException.class)
    public void shouldNotBeAbleToWithdrawMoneyMoreThanBalance() throws ParseException {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 8));

        //when
        bank.handleRequest(withdraw(mike, 10));

        //then
        //throw an exception
    }

    @Test
    public void shouldNotHandleAnyRequestIfCustomerWasNotAdded() {
        //given customer not added
        Customer customer = Customer.createCustomer("aaa", new Date());

        //when
        bank.handleRequest(deposit(customer, 8));

        //then
        assertThat(customer.getAccount().getMoney(), is(0));
    }

    @Test
    public void shouldSentMessageIfCustomerWasAdded() throws ParseException {
        Customer customer = Customer.createCustomer("bbb", new Date());
        bank.addCustomer(customer);

        verify(mockedFasterMessageGateway).sendMail(anyString(), anyString());
    }

    @Test
    public void shouldNotSentMessageIfCustomerWasNotAdded() {
        Customer customer = Customer.createCustomer("bbb", new Date());
        verify(mockedFasterMessageGateway, never()).sendMail(anyString(), anyString());
    }

    @Test
    public void shouldSentMessageToManagerIfHavePremiumCustomer() throws ParseException {
        Customer customer = Customer.createCustomer("ccc", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 60000));

        verify(mockedFasterMessageGateway, times(1)).sendMail("manager@thebank.com", customer.getName() + " is now a premium customer");
    }

    @Test
    public void shouldNotSentMessageToManagerAgainIfCustomerIsPremiunOnce() throws ParseException {
        Customer customer = Customer.createCustomer("ccc", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 60000));
        bank.handleRequest(deposit(customer, 10000));
        bank.handleRequest(withdraw(customer, 60000));

        verify(mockedFasterMessageGateway, times(2)).sendMail(anyString(), anyString());
    }

    @Test
    public void shouldSetJoiningDateIfCustomerAddedToBank() throws ParseException {
        Customer customer = Customer.createCustomer("ddd", new Date());
        Date date = new Date();
        bank.addCustomer(customer);

        assertThat(customer.getJoiningDate().getDay(), is(date.getDay()));
    }

    @Test
    public void shouldNotSetJoiningDateIfCustomerNotAddedToBank() {
        Customer customer = Customer.createCustomer("ddd", new Date());

        assertNull(customer.getJoiningDate());
    }

    @Test
    public void shouldAddBonusWhenOverTwoYears() throws ParseException {
        Customer customer = Customer.createCustomer("ddd", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 6));

        assertThat(customer.getAccount().getMoney(), is(11));
    }
}