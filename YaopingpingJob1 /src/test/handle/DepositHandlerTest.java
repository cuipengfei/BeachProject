package handle;


import entity.Customer;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static request.CustomerRequest.depositRequest;

public class DepositHandlerTest {
    @Test
    public void should_deposit(){
        Customer customer = new Customer("yaopingping", Calendar.getInstance());

        DepositHandler depositHandler = new DepositHandler();

        depositHandler.handle(depositRequest(customer, 500d));

        assertThat(customer.getAccount().getBalance(), is(500d));
    }

    @Test
    public void should_given_bonus_when_deposit_and_join_bank_day_over_2_years()
    {
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,-3);
        Customer customer = new Customer("yaopingping", Calendar.getInstance());
        customer.setJoinBankDay(calendar);

        DepositHandler depositHandler = new DepositHandler();

        depositHandler.handle(depositRequest(customer, 500d));

        assertThat(customer.getAccount().getBalance(), is(505d));
    }
}