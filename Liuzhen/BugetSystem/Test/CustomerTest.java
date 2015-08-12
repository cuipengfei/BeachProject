import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by zhenliu on 8/13/15.
 */
public class CustomerTest {
    @Test
    public void should_get_a_blank_account_when_a_customer_be_added_in_a_bank() throws Exception {
        Bank bank = new Bank();
        Customer customer = Customer.createCustomer("liuzhen11",new Date());

        bank.add(customer);

        assertThat(customer.getAccount(), is(0.0));
    }


}