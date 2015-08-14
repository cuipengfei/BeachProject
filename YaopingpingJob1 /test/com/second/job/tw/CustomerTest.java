package com.second.job.tw;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by ppyao on 8/12/15.
 */
public class CustomerTest {
    @Test
    public void defaultCustomerisAccount() {
        //given
        Customer customer = new Customer("yaoping", new Date());
        //when
        assertThat(customer.getAccount().getBalance(), is(0.0));
    }

}