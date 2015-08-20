package com.thoughtworks.handlers;

import com.thoughtworks.Customer;
import com.thoughtworks.requests.CustomerRequest;
import com.thoughtworks.requests.RequestType;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DepositHandlerTest {

    @Test
    public void should_not_get_bonus_when_customer_is_join_above_2_years() throws ParseException {
        Customer customer = new Customer("syyan123", new Date());

        Handler handler=new DepositHandler();
        handler.handle(new CustomerRequest(customer,RequestType.Deposit,10000));
        assertThat(customer.getBalance(), is(10005d));
    }
}