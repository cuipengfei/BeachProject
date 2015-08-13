import beach.utils.Account;
import beach.utils.Bank;
import beach.utils.Customer;
import org.junit.Test;

import javax.naming.InsufficientResourcesException;
import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by mlding on 8/12/15.
 */
public class BankTest {
    @Test
    public void canDepositeMoney() throws Exception {
        //given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Account account = new Account(5);
        Customer customer = new Customer("Mike",sdf.parse("1993-08-09"));
        customer.setAccount(account);

        //when
        boolean isSuccess = bank.addCustomer(customer);
        customer.getAccount().depositMoney(2);


        //then
        assertThat(customer.getAccount().getMoney(),is(7));
    }

    @Test
    public void canWithdrawMoney() throws Exception {
        //given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Account account = new Account(5);
        Customer customer = new Customer("Mike",sdf.parse("1993-08-09"));
        customer.setAccount(account);

        //when
        boolean isSuccess = bank.addCustomer(customer);
        customer.getAccount().withdrawMoney(2);

        //then
        assertThat(customer.getAccount().getMoney(),is(3));
    }

    @Test(expected = InsufficientResourcesException.class)
    public void canNotWithdrawMoneyGreaterThanBalance() throws Exception {
        //given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Account account = new Account(5);
        Customer customer = new Customer("Mike",sdf.parse("1993-08-09"));
        customer.setAccount(account);

        //when
        boolean isSuccess = bank.addCustomer(customer);
        customer.getAccount().withdrawMoney(7);

        //then
    }
}