package com.tw.beach.entity;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankTest {
    @Test
    public void bankShouldAcceptValidCustomer() throws Exception {
        Customer laozhang = Customer.createCustomer("laozhang", new Date());
        assertTrue(new Bank().addCustomer(laozhang));
    }

    @Test
    public void bankShouldNotAcceptInvalidCustomer() throws Exception {
        Customer invalidLaoZhang = Customer.createCustomer("!@#laozhang", new Date());
        assertFalse(new Bank().addCustomer(invalidLaoZhang));
    }
}