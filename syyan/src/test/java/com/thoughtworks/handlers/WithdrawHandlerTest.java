package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.exception.OverdrawException;
import com.thoughtworks.requests.CustomerRequest;
import com.thoughtworks.requests.RequestType;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WithdrawHandlerTest {

    private Customer customer;
    private Handler handler;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("syyan123", Calendar.getInstance());
        handler = new WithdrawHandler();
    }

    @Test
    public void should_overdraft_when_customer_is_marked_OverdraftAllowed() {
        customer.setOverdraftAllowed(true);

        handler.handle(new CustomerRequest(customer, RequestType.Withdraw, 200d));
        handler.handle(new CustomerRequest(customer, RequestType.Withdraw, 200d));

        assertThat(customer.getBalance(), is(-400d));
    }

    @Test(expected = OverdrawException.class)
    public void should_not_overdraft_when_customer_is_remove_OverdraftAllowed() {
        customer.setOverdraftAllowed(true);
        handler.handle(new CustomerRequest(customer, RequestType.Withdraw, 200d));

        customer.setOverdraftAllowed(false);
        handler.handle(new CustomerRequest(customer, RequestType.Withdraw, 200d));
    }

    @Test(expected = OverdrawException.class)
    public void should_not_overdraft_when_customer_over_1000() {
        customer.setOverdraftAllowed(true);

        handler.handle(new CustomerRequest(customer, RequestType.Withdraw, 10000d));
    }
}