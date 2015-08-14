package beach.utils;

import beach.utils.requests.InsufficientFundException;
import org.junit.Test;
import static beach.utils.requests.CustomerRequest.deposit;
import static beach.utils.requests.CustomerRequest.withdraw;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static beach.utils.Customer.*;

/**
 * Created by mlding on 8/12/15.
 */
public class BankTest {

    @Test
    public void shouldAddSuccessWhenGivenValidCustomer() throws Exception {
        //given
        Bank bank = new Bank();
        Customer customer = createValidCustomer("mike", "1993-08-09");

        //when
        boolean isSucess = bank.addCustomer(customer);

        //then
        assertTrue(isSucess);
    }

    @Test
    public void shouldAddFailureWhenGivenInvalidCustomer() throws Exception {
        //given
        Bank bank = new Bank();
        Customer invalidCustomer = createValidCustomer("Mike", "1993-08-09");

        //when
        boolean isSucess = bank.addCustomer(invalidCustomer);

        //then
        assertFalse(isSucess);
    }

    @Test
    public void shouldAddFailureWhenGivenSameName() throws Exception {
        //given
        Bank bank = new Bank();
        Customer customer = createValidCustomer("mike", "1993-08-09");
        Customer customer2 = createValidCustomer("mike", "1993-08-09");

        //when
        boolean isSucess = bank.addCustomer(customer);
        boolean isSucess2 = bank.addCustomer(customer2);

        //then
        assertTrue(isSucess);
        assertFalse(isSucess2);
    }

    @Test
    public void canDepositeMoney() throws Exception {
        Customer customer = createValidCustomer("mike", "1993-08-09");
        Bank bank = new Bank();
        bank.addCustomer(customer);
        bank.handleRequest(deposit(customer,2));
        assertThat(customer.getAccount().getMoney(), is(7));
    }

    @Test
    public void canWithdrawMoney() throws Exception {
        Bank bank = new Bank();
        Customer customer = createValidCustomer("mike", "1993-08-09");
        bank.addCustomer(customer);
        bank.handleRequest(withdraw(customer,1));

        assertThat(customer.getAccount().getMoney(), is(4));
    }

    @Test(expected = InsufficientFundException.class)
    public void canNotWithdrawMoneyGreaterThanBalance() throws Exception {
        Bank bank = new Bank();
        Customer customer = createValidCustomer("mike", "1993-08-09");
        bank.addCustomer(customer);
        bank.handleRequest(withdraw(customer,6));

        assertThat(customer.getAccount().getMoney(), is(5));
    }
}