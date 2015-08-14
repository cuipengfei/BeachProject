package beach.utils;

import org.junit.Test;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static beach.utils.Customer.*;

/**
 * Created by mlding on 8/12/15.
 */
public class CustomerTest {
    @Test
    public void shouldCreateCustomerWhenGivenValidNicknameCustomer() throws Exception {
        Customer customer = createValidCustomer("mike", "1993-08-09");
        assertThat(customer.getNickname(),is("mike"));
    }

    @Test
    public void shouldnotCreateCustomerWhenGivenInvalidNicknameCustomer() throws Exception {
        Customer customer = createValidCustomer("Mike", "1993-08-09");
        assertThat(customer,is(invalidCustomer()));
    }
}