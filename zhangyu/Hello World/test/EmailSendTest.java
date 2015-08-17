import org.junit.Test;
import request.Bank;
import request.Customer;
import request.EmailSend;
import java.text.SimpleDateFormat;
import static org.mockito.Mockito.*;
/**
 * Created by yuzhang on 8/17/15.
 */
public class EmailSendTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void should_message_in_MailBox_equals_WelcomeMessage() throws Exception {
        EmailSend sender = mock(EmailSend.class);

        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank = new Bank(sender);
        bank.addToBank(customer1);

        verify(sender).sendEmail(customer1);
    }
}