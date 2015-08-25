package handle;


import entity.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static request.CustomerRequest.depositRequest;

public class DepositHandlerTest {
    public Customer customer;
    public DepositHandler depositHandler;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("yaopingping", Calendar.getInstance());

        depositHandler = new DepositHandler();
    }

    @Test
    public void should_deposit_the_account_by_name() {
        customer.createAccount("account");

        customer.setJoinBankDay(Calendar.getInstance());

        depositHandler.handle(depositRequest(customer, 500d, "account"));

        assertThat(customer.findAccountByName("account").getBalance(), is(500d));

    }

    @Test
    public void should_given_one_bonus_when_deposit_and_join_bank_day_over_2_years() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, -3);

        customer.setJoinBankDay(calendar);

        customer.createAccount("account");

        depositHandler.handle(depositRequest(customer, 500d, "account"));

        depositHandler.handle(depositRequest(customer, 500d, "current"));

        assertThat(customer.findAccountByName("account").getBalance(), is(505d));

        assertThat(customer.findAccountByName("current").getBalance(), is(500d));
    }

   @Test
    public void should_no_bonus_when_join_bank_day_less_than_2_years() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.YEAR, -1);

        customer.setJoinBankDay(calendar);

        depositHandler.handle(depositRequest(customer, 500d,"current"));

        assertThat(customer.findAccountByName("current").getBalance(), is(500d));
    }
}