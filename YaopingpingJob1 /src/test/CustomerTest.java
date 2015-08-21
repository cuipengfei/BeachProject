import entity.Customer;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertNotNull;

public class CustomerTest {

    @Test
    public void should_create_customer_with_valid_initial_account_given_valid_name() {
        //when
        Customer customer = new Customer("yaoping", Calendar.getInstance());

        //then
        assertNotNull(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_create_a_customer_if_nickname_contains_uppercase() throws Exception {
        new Customer("UPPERCASE", Calendar.getInstance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_create_a_customer_if_nickname_contains_special_characters() throws Exception {
        new Customer("@!@@#", Calendar.getInstance());
    }
}