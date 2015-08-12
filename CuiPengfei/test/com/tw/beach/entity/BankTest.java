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
        //given
        Customer laozhang = Customer.createCustomer("laozhang", new Date());

        //when
        Boolean isSuccessful = new Bank().addCustomer(laozhang);

        //then
        assertTrue(isSuccessful);
    }

    @Test
    public void bankShouldNotAcceptInvalidCustomer() throws Exception {
        //given
        Customer invalidLaoZhang = Customer.createCustomer("!@#laozhang", new Date());

        //when
        Boolean isSuccessful = new Bank().addCustomer(invalidLaoZhang);

        //then
        assertFalse(isSuccessful);
    }

    @Test
    public void bankShouldNotAcceptSameNameCustomer() throws Exception {
        //given
        Bank bank = new Bank();
        Customer firstAbc = Customer.createCustomer("abc", new Date());
        Customer secondAbc = Customer.createCustomer("abc", new Date());

        //when
        Boolean isSuccessful = bank.addCustomer(firstAbc);

        //then
        assertTrue(isSuccessful);

        //when
        Boolean isSecondSuccessful = bank.addCustomer(secondAbc);

        //then
        assertFalse(isSecondSuccessful);
    }

    @Test
    public void shouldBeAbleToDepositMoney() throws Exception {
        //given
        Bank bank = new Bank();
        Customer xiaoming = Customer.createCustomer("xiaoming", new Date());
        bank.addCustomer(xiaoming);

        //when
        bank.handleRequest(deposit(xiaoming, 100));

        //then
        assertThat(xiaoming.account().balance(), is(100));
    }

    @Test
    public void shouldBeAbleToWithdrawMoney() throws Exception {
        //given
        Bank bank = new Bank();
        Customer xiaoming = Customer.createCustomer("xiaoming", new Date());
        bank.addCustomer(xiaoming);
        bank.handleRequest(deposit(xiaoming, 100));

        //when
        bank.handleRequest(withDraw(xiaoming, 10));

        //then
        assertThat(xiaoming.account().balance(), is(90));
    }

    @Test(expected = InsufficientFundException.class)
    public void shouldNotBeAbleToWithdrawMoneyMoreThanBalance() throws Exception {
        //given
        Bank bank = new Bank();
        Customer xiaoming = Customer.createCustomer("xiaoming", new Date());
        bank.addCustomer(xiaoming);
        bank.handleRequest(deposit(xiaoming, 100));

        //when
        bank.handleRequest(withDraw(xiaoming, 200));

        //then
        //an exception should be thrown(see annotation)
    }
}