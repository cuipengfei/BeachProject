import email.EmailSend;
import email.FasterEmailSend;
import email.MockEmailSend;
import exception.OverDrawException;
import request.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static request.CustomerRequest.deposit;

public class BankTest {

    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    EmailSend sender = new EmailSend();
    Bank bank1 = new Bank(sender);

    @Test
    public void should_balance_equals_300_when_deposit_300() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank1.addToBank(customer1);

        bank1.handleRequest(deposit(customer1, 300));
    }

    @Test
    public void should_balance_equals_200_when_withdraw_100() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank1.addToBank(customer1);

        bank1.handleRequest(deposit(customer1, 300));

        assertThat(bank1.handleRequest(CustomerRequest.withdraw(customer1, 100)), is(200));
    }

    @Test(expected = OverDrawException.class)
    public void should_throw_exception_when_overdraw() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank1.addToBank(customer1);

        bank1.handleRequest(deposit(customer1, 300));
        bank1.handleRequest(CustomerRequest.withdraw(customer1, 301));
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_the_no_added_customer_deposit() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));

        bank1.handleRequest(deposit(customer1, 300));
    }

    @Test
    public void should_call_sendEmail_when_addToBank() throws Exception {
        EmailSend sender = mock(EmailSend.class);

        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);
        bank.addToBank(customer1);

        String content = "Dear " + customer1.getNickname() + " , Welcome to bank";
        verify(sender).sendEmail(customer1.getEmailAddress(), content);
    }

    @Test
    public void should_isCallEmailSend_is_true_when_addToBank() throws Exception {
        MockEmailSend mockSender = new MockEmailSend();

        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(mockSender);
        bank.addToBank(customer1);

        assertTrue(mockSender.isCallEmailSend());
    }

    @Test
    public void should_manager_receive_mail_when_customer_turn_to_premium() throws Exception {
        MockEmailSend mockSender = new MockEmailSend();
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(mockSender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40000));

        assertTrue(mockSender.isCallEmailSend());
    }

    @Test
    public void should_call_sendEmail_to_manager_when_customer_turn_to_premium() throws Exception {
        EmailSend sender = mock(EmailSend.class);
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40000));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, times(1)).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_manager_should_only_ever_receive_email_once() throws Exception {
        EmailSend sender = mock(EmailSend.class);
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40000));
        bank.handleRequest(deposit(customer1, 20000));
        bank.handleRequest(CustomerRequest.withdraw(customer1, 40000));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, times(1)).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_never_call_sendEmail_if_customer_is_not_premium() throws Exception {
        EmailSend sender = mock(EmailSend.class);
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 39999));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, never()).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_instead_EmailSend_when_using_FasterEmailSend() throws Exception {
        FasterEmailSend sender = mock(FasterEmailSend.class);
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40001));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, times(1)).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_dataOfJoin_equals_the_date_customer_was_added_to_bank() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Date date = new Date();

        bank1.addToBank(customer1);
        assertTrue(customer1.getDateOfJoin().equals(date));
    }

    @Test
    public void should_get_bonus_when_customer_added_to_bank_for_two_year() throws Exception {
        EmailSend sender = new EmailSend();
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40001, dataFormat.parse("2017-08-30")));

        assertThat(customer1.getMyAccount().getBalance(),is(40006));
    }

    @Test
    public void should_customer_only_get_bonus_once_() throws Exception {
        EmailSend sender = new EmailSend();
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank = new Bank(sender);

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40000, dataFormat.parse("2017-08-30")));
        bank.handleRequest(deposit(customer1, 10000, dataFormat.parse("2017-08-31")));

        assertThat(customer1.getMyAccount().getBalance(),is(50005));
    }
}