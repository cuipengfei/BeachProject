import org.junit.Test;
import request.Bank;
import request.Customer;
import request.Email;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
/**
 * Created by yuzhang on 8/17/15.
 */
public class EmailTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void should_message_in_MailBox_equals_WelcomeMessage() throws Exception {
        Email email = mock(Email.class);

        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        email.sendEmail(customer1);

        verify(email).sendEmail(customer1);
    }
}