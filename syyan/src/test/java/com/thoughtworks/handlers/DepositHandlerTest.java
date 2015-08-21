package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.requests.CustomerRequest;
import com.thoughtworks.requests.RequestType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DepositHandlerTest {

    private Customer customer;
    private Handler handler;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("syyan123", Calendar.getInstance());
        handler = new DepositHandler();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -3);
        customer.setDateOfJoin(calendar);
    }

    @Test
    public void should_get_bonus_when_customer_is_join_above_2_years() throws ParseException {
        handler.handle(new CustomerRequest(customer, RequestType.Deposit, 10000d));

        assertThat(customer.getBalance(), is(10005d));
    }

    @Test
    public void should_get_bonus_only_once_when_customer_is_join_above_2_years() throws ParseException {
        handler.handle(new CustomerRequest(customer, RequestType.Deposit, 10000d));
        handler.handle(new CustomerRequest(customer, RequestType.Deposit, 10000d));

        assertThat(customer.getBalance(), is(20005d));
    }
}