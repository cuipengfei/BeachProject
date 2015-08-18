package bank.domain.aggregator;

import bank.domain.exception.InvalidEmailAddressExcpetion;
import bank.domain.exception.InvalidCustomerException;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CustomerTest {
    @Test(expected = InvalidCustomerException.class)
    public void should_not_create_customer_with_uppercase_nickname_and_birth_day() throws Exception {
        new Customer("UPPERCASE", new Date());
    }

    @Test(expected = InvalidCustomerException.class)
    public void should_not_create_customer_with_special_character_and_birth_day() throws Exception {
        new Customer("@!*~", new Date());
    }

    @Test(expected = InvalidCustomerException.class)
    public void should_not_create_customer_with_valid_nickname_but_no_birth_day() throws Exception {
        new Customer("nick", null);
    }

    @Test
    public void should_create_customer_with_valid_nickname_and_birth_day() throws Exception {
        Customer nick = new Customer("nick", new Date());
        assertNotNull(nick);
    }

    @Test(expected = InvalidEmailAddressExcpetion.class)
    public void cannot_set_invalid_email_address() throws Exception {
        Customer nick = new Customer("nick", new Date());
        nick.email("nick_not_email");
    }

    @Test
    public void should_set_valid_email_address() throws Exception {
        Customer nick = new Customer("nick", new Date());
        nick.email("nick@bank.com");
        assertThat(nick.email(), is("nick@bank.com"));
    }
}
