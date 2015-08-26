package beach.tw.handlers;

import beach.tw.entity.Customer;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static beach.tw.requests.CustomerRequest.deposit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mlding on 8/25/15.
 */
public class DepositHandlerTest {

    @Test
    public void should_deposit_money() throws Exception {
        DepositHandler depositHandler = new DepositHandler();
        Customer mike = Customer.createCustomer("mike", new Date());
        mike.setJoiningDate(Calendar.getInstance());

        depositHandler.handle(deposit(mike, 8, "current"));

        assertThat(mike.getAccount("current").getMoney(), is(8));
    }
}