package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.requests.CustomerRequest;
import com.thoughtworks.requests.RequestType;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DepositHandlerTest {

    @Test
    public void should_get_bonus_when_customer_is_join_above_2_years() throws ParseException {
        Customer customer = new Customer("syyan123", new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -3);
        customer.setDateOfJoin(calendar);
        Handler handler = new DepositHandler();

        handler.handle(new CustomerRequest(customer, RequestType.Deposit, 10000));

        assertThat(customer.getBalance(), is(10005d));
    }

    @Test
    public void should_get_bonus_only_once_when_customer_is_join_above_2_years() throws ParseException {
        Customer customer = new Customer("syyan123", new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, -3);
        customer.setDateOfJoin(calendar);
        Handler handler = new DepositHandler();

        handler.handle(new CustomerRequest(customer, RequestType.Deposit, 10000));
        handler.handle(new CustomerRequest(customer, RequestType.Deposit, 10000));

        assertThat(customer.getBalance(), is(20005d));
    }
}