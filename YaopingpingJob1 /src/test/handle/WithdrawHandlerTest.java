package handle;

import entity.Account;
import entity.Customer;
import exception.OverdrawException;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static request.CustomerRequest.withdrawRequest;

public class WithdrawHandlerTest {

    @Test(expected = OverdrawException.class)
    public void should_throw_overdraft_exception_when_overdraft_exceeds_limit() throws Exception {
        WithdrawHandler handler = new WithdrawHandler();

        Customer customer = prepareCustomer(1000d);

        handler.handle(withdrawRequest(customer, 2000d));
    }

    @Test
    public void should_overdraft_when_customer_when_overdraft_does_not_exceed_limit() throws Exception {
        WithdrawHandler handler = new WithdrawHandler();

        Customer customer = prepareCustomer(1000d);

        handler.handle(withdrawRequest(customer, 500d));

        Account account = customer.getAccount();

        assertThat(account.getBalance(), is(-500d));
    }

    @Test
    public void should_with_draw_if_no_overdraft() throws Exception {
        WithdrawHandler handler = new WithdrawHandler();

        Customer customer = prepareCustomer(1000d);

        customer.getAccount().addBalance(3000d);

        handler.handle(withdrawRequest(customer, 2000d));

        Account account = customer.getAccount();

        assertThat(account.getBalance(), is(1000d));
    }

    @Test(expected = OverdrawException.class)
    public void should_throw_over_draw_exception_when_overdraw() throws Exception {
        WithdrawHandler handler = new WithdrawHandler();

        Customer customer = prepareCustomer(1000d);

        customer.setOverdraftAllowed(false);

        handler.handle(withdrawRequest(customer, 2000d));
    }

    private Customer prepareCustomer(double overdraftLimit) {
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        customer.setOverdraftAllowed(true);

        customer.getAccount().setOverdraftLimit(overdraftLimit);

        return customer;
    }
}