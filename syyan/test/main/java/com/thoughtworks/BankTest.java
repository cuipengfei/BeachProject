package main.java.com.thoughtworks;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;


public class BankTest {

    @Test
    public void should_add_success_when_give_the_valid_information() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Customer customer = new Customer("yan123", sdf.parse("2015-08-09"));
        assertThat("add success", is(bank.addCustomer(customer)));

    }

    @Test
    public void should_add_failure_when_give_the_valid_nickName() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Customer notValidCustomer = new Customer("YAN", sdf.parse("2015-08-10"));
        assertThat("add failure", is(bank.addCustomer(notValidCustomer)));
    }

    @Test
    public void should_add_failure_when_give_the_same_nickName() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Customer customer = new Customer("yan123", sdf.parse("2015-08-09"));
        assertThat("add success", is(bank.addCustomer(customer)));
        Customer existCustomer = new Customer("yan123", sdf.parse("2015-08-10"));
        assertThat("add failure", is(bank.addCustomer(existCustomer)));
    }

}
