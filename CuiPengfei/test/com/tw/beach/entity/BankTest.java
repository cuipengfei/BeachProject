package com.tw.beach.entity;

import com.tw.beach.entity.requests.InsufficientFundException;
import org.junit.Test;

import java.util.Date;

import static com.tw.beach.entity.requests.CustomerRequest.deposit;
import static com.tw.beach.entity.requests.CustomerRequest.withDraw;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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

    @Test
    public void bankShouldNotAcceptSameNameCustomer() throws Exception {
        Bank bank = new Bank();

        Customer firstAbc = Customer.createCustomer("abc", new Date());
        assertTrue(bank.addCustomer(firstAbc));

        Customer secondAbc = Customer.createCustomer("abc", new Date());
        assertFalse(bank.addCustomer(secondAbc));
    }

    @Test
    public void shouldBeAbleToDepositMoney() throws Exception {
        Bank bank = new Bank();
        Customer xiaoming = Customer.createCustomer("xiaoming", new Date());

        bank.addCustomer(xiaoming);
        bank.handleRequest(deposit(xiaoming, 100));

        assertThat(xiaoming.account().balance(), is(100));
    }

    @Test
    public void shouldBeAbleToWithdrawMoney() throws Exception {
        Bank bank = new Bank();
        Customer xiaoming = Customer.createCustomer("xiaoming", new Date());

        bank.addCustomer(xiaoming);
        bank.handleRequest(deposit(xiaoming, 100));
        bank.handleRequest(withDraw(xiaoming, 10));

        assertThat(xiaoming.account().balance(), is(90));
    }

    @Test(expected = InsufficientFundException.class)
    public void shouldNotBeAbleToWithdrawMoneyMoreThanBalance() throws Exception {
        Bank bank = new Bank();
        Customer xiaoming = Customer.createCustomer("xiaoming", new Date());

        bank.addCustomer(xiaoming);
        bank.handleRequest(deposit(xiaoming, 100));
        bank.handleRequest(withDraw(xiaoming, 200));
    }
}