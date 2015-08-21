package handle;

import exception.OverDrawException;
import org.junit.Test;
import request.Account;
import request.Customer;
import request.CustomerRequest;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WithdrawHandlerTest {

    Customer customer = new Customer("nick", new Date());

    private Account prepareAccount(int balance) {
        Account account = new Account();
        account.add(balance);
        return account;
    }
    @Test
    public void should_do_basic_withdraw() throws Exception {
        WithdrawHandler handler = new WithdrawHandler(0);
        customer.setMyAccount(prepareAccount(100));

        handler.handle(CustomerRequest.withdraw(customer, 50));

        assertThat(customer.getMyAccount().getBalance(), is(50));
    }

    @Test(expected = OverDrawException.class)
    public void should_throw_exception_when_limit_is_zero_and_overdraw() throws Exception {
        WithdrawHandler handler = new WithdrawHandler(0);
        customer.setMyAccount(new Account());

        handler.handle(CustomerRequest.withdraw(customer, 50));
    }

    @Test(expected = OverDrawException.class)
    public void should_throw_exception_when_overdraft_more_than_1000() throws Exception {
        WithdrawHandler handler = new WithdrawHandler(1000);
        customer.setMyAccount(new Account());

        handler.handle(CustomerRequest.withdraw(customer, 1001));
    }
}