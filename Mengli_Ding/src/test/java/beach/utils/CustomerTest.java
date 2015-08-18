package beach.utils;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mlding on 8/15/15.
 */
public class CustomerTest {
    @Test
    public void shouldCreateInstanceOfCustomer() throws Exception {
        Customer mike1 = Customer.createCustomer("mike1", new Date());
        assertThat(mike1.getName(), is("mike1"));
    }

    @Test
    public void shouldNotCreateInstanceOfCustomerWhenNameInvalid() throws Exception {
        Customer mike1 = Customer.createCustomer("mike@#", new Date());
        assertThat(mike1, is(Customer.invalidCustomer()));
    }

    @Test
    public void shouldHaveEmptyAccountByDefault() throws Exception {
        Customer customer = Customer.createCustomer("mike", new Date());
        assertThat(customer.getAccount().getMoney(), is(0));
    }
}