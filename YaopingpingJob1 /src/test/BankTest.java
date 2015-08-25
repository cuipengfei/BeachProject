import email.MailSendStatus;
import email.MailSender;
import email.MessageGateway;
import entity.Bank;
import entity.Customer;
import exception.OverdrawException;
import org.junit.Before;
import org.junit.Test;
import utils.FileUtils;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static request.CustomerRequest.depositRequest;
import static request.CustomerRequest.withdrawRequest;


public class BankTest {
    public MessageGateway sender;
    public Bank bank;
    public Calendar calendar;

    @Before
    public void setUp() throws Exception {
        sender = new MailSender();
        bank = new Bank(sender);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -3);

    }

    @Test
    public void should_add_customer_when_does_not_exist_() {
        //given
        Customer customer = new Customer("yaoping1", Calendar.getInstance());

        //when
        boolean isSuccess = bank.addCustomer(customer);

        //then
        assertTrue(isSuccess);
    }

    @Test
    public void should_not_add_customer_when_exist() {
        //given
        Customer firstCustomer = new Customer("yaoping", Calendar.getInstance());

        Customer secondCustomer = new Customer("yaoping", Calendar.getInstance());
        //when
        boolean isFirstSuccess = bank.addCustomer(firstCustomer);

        boolean isSecondSuccess = bank.addCustomer(secondCustomer);

        //then
        assertTrue(isFirstSuccess);

        assertFalse(isSecondSuccess);
    }

    @Test
    public void should_call_send_welcome_email_to_customer_after_customer_was_added() {
        //given
        MessageGateway sender = mock(MailSender.class);

        when(sender.sendEmail(anyString(), anyString())).thenReturn(MailSendStatus.OK);

        bank = new Bank(sender);

        Customer customer = new Customer("yaopingping1", Calendar.getInstance());

        //when
        bank.addCustomer(customer);

        //then
        verify(sender).sendEmail(customer.getEmailAddress(), "Dear <yaopingping1>, Welcome to the Bank");
    }


    @Test
    public void should_call_send_mail_method_to_manager_when_customer_balance_over_40000() throws OverdrawException {
        //given
        MessageGateway sender = mock(MailSender.class);

        when(sender.sendEmail(anyString(), anyString())).thenReturn(MailSendStatus.OK);

        bank = new Bank(sender);

        Customer customer = new Customer("yaopingping2", Calendar.getInstance());
        //when
        bank.addCustomer(customer);

        bank.handleRequest(depositRequest(customer, 40000d,"current"));

        bank.handleRequest(depositRequest(customer, 10000d,"current"));
        //then
        verify(sender, times(2)).sendEmail(anyString(), anyString());
    }

    @Test
    public void should_no_send_email_to_manager_when_customer_balance_less_than_40000() throws OverdrawException {
        //given
        MessageGateway sender = mock(MailSender.class);

        when(sender.sendEmail(anyString(), anyString())).thenReturn(MailSendStatus.OK);

        bank = new Bank(sender);

        Customer customer = new Customer("yaopingping3", Calendar.getInstance());

        //when
        bank.addCustomer(customer);

        bank.handleRequest(depositRequest(customer, 30000d,"current"));

        //then
        verify(sender, never()).sendEmail(bank.bankManager.getEmailAddress(), "yaopingping3 is a premium customer");
    }

    @Test
    public void should_handle_the_request_when_added_to_bank() throws OverdrawException {
        Customer customer = new Customer("yaopingping4", Calendar.getInstance());

        bank.addCustomer(customer);

        bank.handleRequest(depositRequest(customer, 100d, "current"));

        bank.handleRequest(withdrawRequest(customer, 50d, "current"));

        assertThat(customer.findAccountByName("current").getBalance(),is(50d));
    }

    @Test
    public void should_write_log_successfully() {
        MailSender sender = mock(MailSender.class);

        when(sender.sendEmail(anyString(), anyString())).thenReturn(MailSendStatus.OK);

        bank = new Bank(sender);

        Customer customer = new Customer("yaopingping5", Calendar.getInstance());

        bank.addCustomer(customer);

        verify(sender).sendEmail(anyString(), anyString());

        assertTrue(FileUtils.isCalledWriteMessageLog());
    }
}