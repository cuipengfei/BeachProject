import email.FasterMessageGateway;
import email.MailSender;
import email.MessageGateway;
import entity.Bank;
import entity.Customer;
import exception.OverdrawException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static request.CustomerRequest.depositRequest;
import static request.CustomerRequest.withdrawRequest;

/**
 * Created by ppyao on 8/12/15.
 */
public class BankTest {
    public MailSender sender;
    public Bank bank;
    public Calendar birthday;
    Calendar calendar;

    @Before
    public void setUp() throws Exception {
        sender = new MailSender();
        bank = new Bank(sender);
        birthday = Calendar.getInstance();
        birthday.set(1999, 4, 1);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -3);
    }

    @Test
    public void bankAcceptValidCustomer() {
        //given
        Customer customer = new Customer("yaoping", birthday);
        //when
        boolean isSuccess = bank.addCustomer(customer);

        //then
        assertTrue(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenCustomerExist() {
        Customer firstCustomer = new Customer("yaoping", birthday);
        Customer secondCustomer = new Customer("yaoping", birthday);
        //when
        boolean isFirstSuccess = bank.addCustomer(firstCustomer);
        boolean isSecondSuccess = bank.addCustomer(secondCustomer);

        //then
        assertTrue(isFirstSuccess);
        assertFalse(isSecondSuccess);
    }

    @Test
    public void bankShouldDespoitMoney() throws OverdrawException {
        Customer customer = new Customer("yaoping", birthday);
        bank.addCustomer(customer);
        //when
        bank.handleRequest(depositRequest(customer, 5000.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(5000.0));
    }

    @Test
    public void bankShouldWithdrawMoneyWhenMoneyLessThanBalance() throws Exception {
        Customer customer = new Customer("yaoping", birthday);
        bank.addCustomer(customer);
        //when
        bank.handleRequest(depositRequest(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 50.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(50.0));
    }

    @Test(expected = OverdrawException.class)
    public void bankShouldNotWithdrawMoneyWhenMoneyLargerThanBalance() throws OverdrawException {
        Customer customer = new Customer("yaoping", birthday);
        bank.addCustomer(customer);
        //when
        bank.handleRequest(depositRequest(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 150.0));
    }

    @Test
    public void bandActualSendEamil() {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", birthday);
        //when
        bank.addCustomer(customer);

        //then
        verify(sender).sendEmail(customer.getEmailAddress(), "Dear <yaopingping>,Welcome to the Bank");
    }


    @Test
    public void bankShouldAddToPreminumWhenCustomerBalanceMoreThan40000() throws OverdrawException {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", birthday);
        //when
        bank.addCustomer(customer);
        bank.handleRequest(depositRequest(customer, 40000.0));

        //then
        verify(sender).sendEmail(bank.bankManager.getEmailAddress(), "yaopingping is a premium customer");
    }

    @Test
    public void bankShouldNotAddToPreminumWhenCustomerBalanceLessThan40000() throws OverdrawException {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", birthday);
        //when
        bank.addCustomer(customer);
        bank.handleRequest(depositRequest(customer, 30000.0));

        //then
        verify(sender, never()).sendEmail(bank.bankManager.getEmailAddress(), "yaopingping is a premium customer");
    }

    @Test
    public void bankShouldAddPreminumOnce() throws OverdrawException {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaoping", birthday);
        //when
        bank.addCustomer(customer);
        bank.handleRequest(depositRequest(customer, 40000.0));
        bank.handleRequest(depositRequest(customer, 10000.0));

        verify(sender, times(1)).sendEmail(matches(bank.bankManager.getEmailAddress()), matches("yaoping is a premium customer"));
    }

    @Test
    public void bankSendEmailToBankmanagerByFasterMessageGateway() throws OverdrawException {
        MessageGateway sender = mock(FasterMessageGateway.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaoping", birthday);
        //when
        bank.addCustomer(customer);
        bank.handleRequest(depositRequest(customer, 40000.0));

        verify(sender).sendEmail(matches(bank.bankManager.getEmailAddress()), matches("yaoping is a premium customer"));
    }

    @Test
    public void bankShouldGiveRewardWhenJoinBankDayMoreThan2() throws OverdrawException {
        //given
        Customer customer = new Customer("yaoping", birthday);

        //when
        bank.addCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(depositRequest(customer, 40000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(40005.0));

    }

    @Test
    public void bankShouldNotGiveRewardWhenJoinBankDayLessThan2() throws OverdrawException {
        //given
        Customer customer = new Customer("yaoping", birthday);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        //when
        bank.addCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(depositRequest(customer, 40000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(40000.0));
    }

    @Test
    public void bankShouldNotGiveRewardWhenCustomerIsAcceptedReward() throws OverdrawException {
        Customer customer = new Customer("yaoping", birthday);

        //when
        bank.addCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(depositRequest(customer, 40000.0));
        bank.handleRequest(depositRequest(customer, 10000.0));
        assertThat(customer.getAccount().getBalance(), is(50005.0));
    }

    @Test
    public void bankShouldGiveRewardWhenDespoit() throws OverdrawException {
        //given
        Customer customer = new Customer("yaoping", birthday);

        //when
        bank.addCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(depositRequest(customer, 40000.0));
        bank.handleRequest(withdrawRequest(customer, 10000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(30005.0));
    }

    @Test(expected = OverdrawException.class)
    public void withdrawNotAllowedWhenOverdraftRejected() throws OverdrawException {
        Customer customer = new Customer("yaoping", birthday);

        bank.addCustomer(customer);

        customer.setOverdraftAllowed(false);

        bank.handleRequest(withdrawRequest(customer, 100.0));
    }


}