package com.tw.beach.entity;

import org.junit.Test;

import java.util.Date;

import static com.tw.beach.entity.Customer.createCustomer;
import static com.tw.beach.entity.Customer.invalidCustomer;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {
    @Test
    public void shouldCreateInstanceOfCustomer() throws Exception {
        Customer zhangsan123 = createCustomer("zhangsan123", new Date());
        assertThat(zhangsan123.nickName(), is("zhangsan123"));
    }

    @Test
    public void shouldNotCreateInstanceOfCustomerWhenNameInvalid() throws Exception {
        Customer zhangsan123 = createCustomer("zhangsan123!!!", new Date());
        assertThat(zhangsan123, is(invalidCustomer()));
    }

    @Test
    public void shouldHaveEmptyAccountByDefault() throws Exception {
        Customer lisi = createCustomer("lisi456", new Date());
        assertThat(lisi.account().balance(),is(0));
    }
}