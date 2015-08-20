package entity;

import email.MailSender;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertTrue;


/**
 * Created by ppyao on 8/20/15.
 */
public class BankManagerTest {
    public MailSender sender;
    public Bank bank;
    public Calendar birthday;
    public Customer customer;

    @Before
    public void setUp() throws Exception {
        sender = new MailSender();
        bank = new Bank(sender);
        birthday = Calendar.getInstance();
        birthday.set(1999, 4, 1);
        customer = new Customer("yaoping", birthday);


    }

    @Test
    public void testCustomerJoinBankDay() {
        bank.AddCustomertoBankwhenValidCustomer(customer);
        boolean isjoinBankDayMoreThan2 = BankManager.calInterval(customer);
        assertTrue(isjoinBankDayMoreThan2);
    }


}