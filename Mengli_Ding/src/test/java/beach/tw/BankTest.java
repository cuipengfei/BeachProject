package beach.tw;

import beach.tw.entity.Account;
import beach.tw.entity.Bank;
import beach.tw.entity.Customer;
import beach.tw.exception.InsufficientException;
import beach.tw.external.FasterMessageGateway;
import beach.tw.external.Status;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static beach.tw.requests.CustomerRequest.deposit;
import static beach.tw.requests.CustomerRequest.transfer;
import static beach.tw.requests.CustomerRequest.withdraw;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Created by mlding on 8/15/15.
 */
public class BankTest {

    private Bank bank;
    private FasterMessageGateway messageGateway;

    @Before
    public void setUp() {
        messageGateway = mock(FasterMessageGateway.class);
        bank = new Bank(messageGateway);
    }

    @Test
    public void BankShouldAddValidCustomer() throws Exception {
        //given
        Customer miya11 = Customer.createCustomer("miya11", new Date());
        FasterMessageGateway fasterMessageGateway2 = new FasterMessageGateway();

        //when
        boolean isSuccessful = new Bank(fasterMessageGateway2).addCustomer(miya11);

        //then
        assertTrue(isSuccessful);
    }

    @Test
    public void BankShouldNotAddInvalidCustomer() throws Exception {
        //given
        Customer Miya = Customer.createCustomer("Miya", new Date());

        //when
        boolean isSuccessful = bank.addCustomer(Miya);

        //then
        assertFalse(isSuccessful);
    }

    @Test
    public void BankShouldNotAddSameNameCustomer() throws Exception {
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
    public void shouldBeAbleToDepositMoney() throws Exception {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);

        //when
        bank.handleRequest(deposit(mike, 8));

        //then
        assertThat(mike.getAccount("current").getMoney(), is(8));
    }

    @Test
    public void shouldBeAbleToWithdrawMoney() throws Exception {
        //given
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 8));

        //when
        bank.handleRequest(withdraw(mike, 2));

        //then
        assertThat(mike.getAccount("current").getMoney(), is(6));
    }

    @Test(expected = InsufficientException.class)
    public void shouldNotBeAbleToWithdrawMoneyMoreThanBalance() throws Exception {
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
    public void shouldSentMessageIfCustomerWasAdded() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);

        verify(messageGateway).sendMail(anyString(), anyString());
    }

    @Test
    public void shouldNotSentMessageIfCustomerWasNotAdded() {
        Customer customer = Customer.createCustomer("ding", new Date());

        verify(messageGateway, never()).sendMail(anyString(), anyString());
    }

    @Test
    public void shouldSentMessageToManagerIfHavePremiumCustomer() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 60000));

        verify(messageGateway, times(1)).sendMail("manager@thebank.com", customer.getName() + " is now a premium customer");
    }

    @Test
    public void shouldNotSentMessageToManagerAgainIfCustomerIsPremiunOnce() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer, 60000));
        bank.handleRequest(deposit(customer, 10000));
        bank.handleRequest(withdraw(customer, 60000));

        verify(messageGateway, times(2)).sendMail(anyString(), anyString());
    }

    @Test
    public void shouldSetJoiningDateIfCustomerAddedToBank() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        bank.addCustomer(customer);

        assertThat(customer.getJoiningDate().get(Calendar.DAY_OF_MONTH), is(calendar.get(Calendar.DAY_OF_MONTH)));
    }

    @Test
    public void shouldNotSetJoiningDateIfCustomerNotAddedToBank() {
        Customer customer = Customer.createCustomer("ding", new Date());

        assertNull(customer.getJoiningDate());
    }

    @Test
    public void shouldAddBonusWhenOverTwoYears() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        Calendar calendar = customer.getJoiningDate();
        calendar.add(Calendar.YEAR, -3);
        customer.setJoiningDate(calendar);

        bank.handleRequest(deposit(customer, 6));
        bank.handleRequest(deposit(customer, 7));

        assertThat(customer.getAccount("current").getMoney(), is(18));
    }

    @Test
    public void shouldOverdraftWhenMarked() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        customer.getAccount("current").setIsOverdraft(true);
        customer.getAccount("current").setLimit(1000);

        bank.handleRequest(deposit(customer, 200));
        bank.handleRequest(withdraw(customer, 300));
        bank.handleRequest(withdraw(customer, 900));

        assertThat(customer.getAccount("current").getMoney(), is(-1000));
    }

    @Test(expected = InsufficientException.class)
    public void shouldNotContinueOverdraftWhenRemoveMarked() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);
        customer.getAccount("current").setIsOverdraft(true);
        customer.getAccount("current").setLimit(1000);

        bank.handleRequest(deposit(customer, 200));
        bank.handleRequest(withdraw(customer, 300));

        assertThat(customer.getAccount("current").getMoney(), is(-100));

        customer.getAccount("current").setIsOverdraft(false);

        bank.handleRequest(withdraw(customer, 300));

        //throw an exception
    }

    @Test(expected = InsufficientException.class)
    public void shouldNotOverdraftWhenNotMarked() throws Exception {
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);

        bank.handleRequest(deposit(customer, 200));
        bank.handleRequest(withdraw(customer, 300));

        //throw an exception
    }

    @Test
    public void isLogMessageSuccessful() throws Exception {
        when(messageGateway.sendMail(anyString(), anyString())).thenReturn(Status.OK);
        Customer customer = Customer.createCustomer("ding", new Date());
        bank.addCustomer(customer);

        verify(messageGateway).sendMail(anyString(), anyString());
        assertTrue(bank.isCalls());
    }

    @Test
    public void customerShouldHaveDefaultAccount() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());
        bank.addCustomer(ding);

        assertNotNull(ding.getAccount("current"));
    }

    @Test
    public void shouldNotCreateSameNameAccount() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());
        bank.addCustomer(ding);
        Account current = new Account("current");

        boolean isSuccessful = ding.addAccount(current);

        assertFalse(isSuccessful);
    }

    @Test
    public void shouldCreateValidAccount() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());
        bank.addCustomer(ding);
        Account first = new Account("first");

        boolean isSuccessful = ding.addAccount(first);

        assertTrue(isSuccessful);
    }

    @Test
    public void customerShouldRequestTotalBalance() throws Exception {
        Customer ding = Customer.createCustomer("ding", new Date());
        bank.addCustomer(ding);
        bank.handleRequest(deposit(ding, 200));

        assertThat(ding.getAccount("current").getMoney(), is(200));

        bank.handleRequest(withdraw(ding, 100));

        assertThat(ding.getAccount("current").getMoney(), is(100));
    }

    @Test
    public void shouldBeAbleToTransferMoney() throws Exception {
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 800));
        Account first = new Account("first");
        mike.addAccount(first);

        bank.handleRequest(transfer(mike, mike.getAccount("current"), mike.getAccount("first"), 200));

        assertThat(mike.getAccount("current").getMoney(), is(600));
        assertThat(mike.getAccount("first").getMoney(), is(200));
    }

    @Test(expected = InsufficientException.class)
    public void shouldNotTransferMoneyOverCurrentMoneyEvenBeAbleToOverdraft() throws Exception {
        Customer mike = Customer.createCustomer("mike", new Date());
        bank.addCustomer(mike);
        bank.handleRequest(deposit(mike, 800));
        mike.getAccount("current").setIsOverdraft(true);
        mike.getAccount("current").setLimit(1000);
        Account first = new Account("first");
        mike.addAccount(first);

        bank.handleRequest(transfer(mike, mike.getAccount("current"), mike.getAccount("first"), 1200));

        //throw an exception
    }
}