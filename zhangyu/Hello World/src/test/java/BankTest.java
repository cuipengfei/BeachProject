import email.EmailSend;
import email.FasterEmailSend;
import email.MockEmailSend;
import exception.OverDrawException;
import exception.OverLimitException;
import request.*;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static request.CustomerRequest.deposit;
import static request.CustomerRequest.withdraw;

public class BankTest {

    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    EmailSend sender = mock(EmailSend.class);
    Bank bank = new Bank(sender);

    @Test
    public void should_balance_equals_300_when_deposit_300() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank.addToBank(customer1);

        bank.handleRequest(deposit(customer1, 300));
    }

    @Test
    public void should_balance_equals_200_when_withdraw_100() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank.addToBank(customer1);

        bank.handleRequest(deposit(customer1, 300));

        assertThat(bank.handleRequest(withdraw(customer1, 100)), is(200));
    }

    @Test(expected = OverDrawException.class)
    public void should_throw_exception_when_overdraw() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank.addToBank(customer1);

        bank.handleRequest(deposit(customer1, 300));
        bank.handleRequest(withdraw(customer1, 301));
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_the_no_added_customer_deposit() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));

        bank.handleRequest(deposit(customer1, 300));
    }

    @Test
    public void should_call_sendEmail_when_addToBank() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank.addToBank(customer1);

        String content = "Dear " + customer1.getNickname() + " , Welcome to bank";
        verify(sender).sendEmail(customer1.getEmailAddress(), content);
    }

    @Test
    public void should_isCallEmailSend_is_true_when_addToBank() throws Exception {
        MockEmailSend mockSender = new MockEmailSend();

        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank1 = new Bank(mockSender);
        bank1.addToBank(customer1);

        assertTrue(mockSender.isCallEmailSend());
    }

    @Test
    public void should_manager_receive_mail_when_customer_turn_to_premium() throws Exception {
        MockEmailSend mockSender = new MockEmailSend();
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank1 = new Bank(mockSender);

        bank1.addToBank(customer1);
        bank1.handleRequest(deposit(customer1, 40000));

        assertTrue(mockSender.isCallEmailSend());
    }

    @Test
    public void should_call_sendEmail_to_manager_when_customer_turn_to_premium() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40000));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, times(1)).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_manager_should_only_ever_receive_email_once() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 40000));
        bank.handleRequest(deposit(customer1, 20000));
        bank.handleRequest(CustomerRequest.withdraw(customer1, 40000));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, times(1)).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_never_call_sendEmail_if_customer_is_not_premium() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));

        bank.addToBank(customer1);
        bank.handleRequest(deposit(customer1, 39999));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(sender, never()).sendEmail(bank.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_instead_EmailSend_when_using_FasterEmailSend() throws Exception {
        FasterEmailSend fasterSender = mock(FasterEmailSend.class);
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Bank bank1 = new Bank(fasterSender);

        bank1.addToBank(customer1);
        bank1.handleRequest(deposit(customer1, 40001));

        String content = customer1.getNickname() + " is now a premium customer";
        verify(fasterSender, times(1)).sendEmail(bank1.getManager().getEmailAddress(), content);
    }

    @Test
    public void should_dataOfJoin_equals_the_date_customer_was_added_to_bank() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));

        bank.addToBank(customer1);
        int dayOfJoin = customer1.getDateOfJoin().get(Calendar.DAY_OF_MONTH);
        int addTime = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        assertEquals(dayOfJoin,addTime);
    }

    @Test
    public void should_get_bonus_when_customer_added_to_bank_for_two_year_and_one_day() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Calendar dateOfJoin = Calendar.getInstance();
        bank.addToBank(customer1);

        dateOfJoin.add(Calendar.YEAR, -2);
        dateOfJoin.add(Calendar.DAY_OF_MONTH, -1);
        customer1.setDateOfJoin(dateOfJoin);
        bank.handleRequest(deposit(customer1, 300));
        bank.handleRequest(deposit(customer1, 300));

        assertThat(customer1.getMyAccount().getBalance(),is(605));
    }

    @Test
    public void should_not_get_bonus_when_customer_added_to_bank_for_one_year() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Calendar dateOfJoin = Calendar.getInstance();
        bank.addToBank(customer1);

        dateOfJoin.add(Calendar.YEAR, -1);
        customer1.setDateOfJoin(dateOfJoin);
        bank.handleRequest(deposit(customer1, 300));

        assertThat(customer1.getMyAccount().getBalance(),is(300));
    }

    @Test
    public void should_only_get_once_bonus() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        Calendar dateOfJoin = Calendar.getInstance();
        bank.addToBank(customer1);

        dateOfJoin.add(Calendar.YEAR, -3);
        customer1.setDateOfJoin(dateOfJoin);
        bank.handleRequest(deposit(customer1, 300));
        bank.handleRequest(withdraw(customer1, 300));

        assertThat(customer1.getMyAccount().getBalance(),is(5));
    }

    @Test
    public void should_OverDraftAllowed_Customer_can_withdraw_beyond_zero() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        customer1.setOverdraftAllowed(true);
        bank.addToBank(customer1);

        bank.handleRequest(deposit(customer1, 300));
        bank.handleRequest(withdraw(customer1, 301));

        assertThat(customer1.getMyAccount().getBalance(),is(-1));
    }

    @Test(expected = OverLimitException.class)
    public void should_throw_Exception_when_OverDraftAllowed_customer_OverDrawNum_is_1001() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        customer1.setOverdraftAllowed(true);
        bank.addToBank(customer1);

        bank.handleRequest(deposit(customer1, 1000));
        bank.handleRequest(withdraw(customer1, 2001));
    }

    @Test(expected = OverDrawException.class)
    public void should_throw_Exception_when_customer_whose_balance_is_negative_and_OverDraftAllowed_removed_withdraw() throws Exception {
        Customer customer1 = new Customer("zhangyu", dataFormat.parse("2015-08-11"));
        bank.addToBank(customer1);

        customer1.setOverdraftAllowed(true);
        bank.handleRequest(deposit(customer1, 1000));
        bank.handleRequest(withdraw(customer1, 1001));

        customer1.setOverdraftAllowed(false);
        bank.handleRequest(withdraw(customer1, 1));
    }
}