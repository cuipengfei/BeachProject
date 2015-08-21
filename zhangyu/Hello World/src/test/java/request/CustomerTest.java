package request;

import org.junit.Test;
import request.Bank;
import request.Customer;
import email.EmailSend;

import java.text.SimpleDateFormat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yuzhang on 8/12/15.
 */
public class CustomerTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void should_add_successful_when_nickname_is_valid_and_norepeate() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        EmailSend sender = new EmailSend();
        Bank bank1 = new Bank(sender);
        assertTrue(bank1.addToBank(customer1));
    }
}
