import email.FasterMessageGateway;
import email.MailSender;
import email.MessageGateway;
import entity.Bank;
import entity.Customer;
import exception.OverdraftException;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static request.CustomerRequest.despoitRequst;
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
        boolean isSuccess = bank.addCustomertoBankwhenValidCustomer(customer);

        //then
        assertTrue(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenNicknameInValidate() {
        Customer customer = new Customer("Yaoping", birthday);
        //when
        boolean isSuccess = bank.addCustomertoBankwhenValidCustomer(customer);

        // then
        assertFalse(isSuccess);
    }

    @Test
    public void bankShouldUnacceptCustomerWhenCustomerExist() {
        Customer firstCustomer = new Customer("yaoping", birthday);
        Customer secondCustomer = new Customer("yaoping", birthday);
        //when
        boolean isFirstSuccess = bank.addCustomertoBankwhenValidCustomer(firstCustomer);
        boolean isSecondSuccess = bank.addCustomertoBankwhenValidCustomer(secondCustomer);

        //then
        assertTrue(isFirstSuccess);
        assertFalse(isSecondSuccess);
    }

    @Test
    public void bankShouldDespoitMoney() throws OverdraftException {
        Customer customer = new Customer("yaoping", birthday);
        bank.addCustomertoBankwhenValidCustomer(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 5000.0));
        //then
        assertThat(customer.getAccount().getBalance(), is(5000.0));
    }

    @Test
    public void bankShouldWithdrawMoneyWhenMoneyLessThanBalance() throws Exception {
        Customer customer = new Customer("yaoping", birthday);
        bank.addCustomertoBankwhenValidCustomer(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 50.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(50.0));
    }

    @Test(expected = OverdraftException.class)
    public void bankShouldNotWithdrawMoneyWhenMoneyLargerThanBalance() throws OverdraftException {
        Customer customer = new Customer("yaoping", birthday);
        bank.addCustomertoBankwhenValidCustomer(customer);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 150.0));
    }

    @Test
    public void bandShouldNotAcceptAnyRequestWhenCustomerNotAdd() throws OverdraftException {
        Customer customer = new Customer("yaoping", birthday);
        //when
        bank.handleRequest(despoitRequst(customer, 100.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(0.0));
    }

    @Test
    public void bandActualSendEamil() {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", birthday);
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);

        //then
        verify(sender).sendEmail(customer.getEmailAddress(), "Dear <yaopingping>,Welcome to the Bank");
    }


    @Test
    public void bankShouldAddToPreminumWhenCustomerBalanceMoreThan40000() throws OverdraftException {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", birthday);
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        bank.handleRequest(despoitRequst(customer, 40000.0));

        //then
        verify(sender).sendEmail(bank.bankManager.getEmailAddress(), "yaopingping is a premium customer");
    }

    @Test
    public void bankShouldNotAddToPreminumWhenCustomerBalanceLessThan40000() throws OverdraftException {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaopingping", birthday);
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        bank.handleRequest(despoitRequst(customer, 30000.0));

        //then
        verify(sender, never()).sendEmail(bank.bankManager.getEmailAddress(), "yaopingping is a premium customer");
    }

    @Test
    public void bankShouldAddPreminumOnce() throws OverdraftException {
        //given
        MessageGateway sender = mock(MailSender.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaoping", birthday);
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        bank.handleRequest(despoitRequst(customer, 40000.0));
        bank.handleRequest(despoitRequst(customer, 10000.0));

        verify(sender, times(1)).sendEmail(matches(bank.bankManager.getEmailAddress()), matches("yaoping is a premium customer"));
    }

    @Test
    public void bankSendEmailToBankmanagerByFasterMessageGateway() throws OverdraftException {
        MessageGateway sender = mock(FasterMessageGateway.class);
        bank = new Bank(sender);
        Customer customer = new Customer("yaoping", birthday);
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        bank.handleRequest(despoitRequst(customer, 40000.0));

        verify(sender).sendEmail(matches(bank.bankManager.getEmailAddress()), matches("yaoping is a premium customer"));
    }

    @Test
    public void bankShouldGiveRewardWhenJoinBankDayMoreThan2() throws OverdraftException {
        //given
        Customer customer = new Customer("yaoping", birthday);

        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(despoitRequst(customer, 40000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(40005.0));

    }

    @Test
    public void bankShouldNotGiveRewardWhenJoinBankDayLessThan2() throws OverdraftException {
        //given
        Customer customer = new Customer("yaoping", birthday);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(despoitRequst(customer, 40000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(40000.0));
    }

    @Test
    public void bankShouldNotGiveRewardWhenCustomerIsAcceptedReward() throws OverdraftException {
        Customer customer = new Customer("yaoping", birthday);

        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(despoitRequst(customer, 40000.0));
        bank.handleRequest(despoitRequst(customer, 10000.0));
        assertThat(customer.getAccount().getBalance(), is(50005.0));
    }

    @Test
    public void bankShouldGiveRewardWhenDespoit() throws OverdraftException {
        //given
        Customer customer = new Customer("yaoping", birthday);

        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        customer.setJoinBankDay(calendar);
        bank.handleRequest(despoitRequst(customer, 40000.0));
        bank.handleRequest(withdrawRequest(customer, 10000.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(30005.0));
    }


    @Test
    public void withdrawMoreThanCustomerBalanceWhenOverdraftAllowed() throws OverdraftException {
        Customer customer = new Customer("yaoping", Calendar.getInstance());
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        customer.setOverdraftAllowed(true);
        bank.handleRequest(despoitRequst(customer, 40000.0));
        bank.handleRequest(withdrawRequest(customer, 40500.0));

        //then
        assertThat(customer.getAccount().getBalance(), is(500.0));
    }

    @Test(expected = OverdraftException.class)
    public void rejectWithdrawMoreThanCustomerBalanceWhenOverdraftCancel() throws OverdraftException {
        Customer customer = new Customer("yaoping", birthday);
        //when
        bank.addCustomertoBankwhenValidCustomer(customer);
        customer.setOverdraftAllowed(false);
        bank.handleRequest(despoitRequst(customer, 100.0));
        bank.handleRequest(withdrawRequest(customer, 150.0));
    }

}