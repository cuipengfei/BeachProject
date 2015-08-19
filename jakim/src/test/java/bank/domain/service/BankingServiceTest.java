package bank.domain.service;

import bank.domain.aggregator.Customer;
import bank.domain.event.Event;
import bank.domain.event.handler.Handler;
import bank.domain.exception.OverdrawException;
import bank.infrastructure.CustomerRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class BankingServiceTest {

    private CustomerRepository customerRepository;
    private BankingService bankingService;

    @Before
    public void setUp() throws Exception {
        customerRepository = new CustomerRepository();
        bankingService = new BankingService(customerRepository);
        prepareCustomer("jakim");
    }

    @Test
    public void should_deposit_to_customer_account() throws Exception {
        float balance = bankingService.deposit(new Customer("jakim", new Date()), 30f);

        assertThat(balance, is(30f));
        assertTrue(customerRepository.findByNickname("jakim").isPresent());
        assertThat(customerRepository.findByNickname("jakim").get().getAccount().balance(), is(30f));
    }

    @Test
    public void should_withdraw_from_customer_account() throws Exception {
        Customer customer = new Customer("jakim", new Date());

        float balance = bankingService.deposit(customer, 30f);

        assertThat(balance, is(30f));

        float newBalance = bankingService.withdraw(customer, 20f);

        assertThat(newBalance, is(10f));
        assertThat(customerRepository.findByNickname("jakim").get().getAccount().balance(), is(10f));
    }

    @Test
    public void deposit_should_fire_new_premium_event() throws Exception {
        Customer customer = new Customer("jakim", new Date());

        Handler handler = mock(Handler.class);
        Event.addHandler(handler);

        when(handler.shouldHandle(anyObject())).thenReturn(true);

        float balance = bankingService.deposit(customer, 40005f);

        assertThat(balance, is(40005f));
        verify(handler).handle(any(Event.class));
    }

    @Test
    public void should_not_fire_event_for_a_existed_premium_customer() throws Exception {
        Customer customer = prepareCustomer("jakim");
        customer.upgrade();

        Handler handler = mock(Handler.class);
        Event.addHandler(handler);

        when(handler.shouldHandle(anyObject())).thenReturn(true);

        float balance = bankingService.deposit(customer, 40005f);

        assertThat(balance, is(40005f));
        verify(handler, never()).handle(any(Event.class));
    }

    @Test(expected = OverdrawException.class)
    public void should_throw_exception_when_overdraw() throws Exception {
        Customer customer = new Customer("jakim", new Date());

        float balance = bankingService.deposit(customer, 30f);

        assertThat(balance, is(30f));

        bankingService.withdraw(customer, 50f);
    }

    @Test
    public void should_withdraw_all() throws Exception {
        Customer customer = new Customer("jakim", new Date());
        float balance = bankingService.deposit(customer, 30f);

        assertThat(balance, is(30f));
        assertThat(customerRepository.findByNickname("jakim").get().getAccount().balance(), is(30f));

        float newBalance = bankingService.withdrawAll(customer);
        assertThat(newBalance, is(0f));
        assertThat(customerRepository.findByNickname("jakim").get().getAccount().balance(), is(0f));
    }

    private Customer prepareCustomer(String nickname) {
        Customer customer = new Customer(nickname, new Date());
        customerRepository.save(customer);
        return customer;
    }
}