package bank.domain.service;

import bank.domain.aggregator.Customer;
import bank.domain.exception.OverdrawException;
import bank.infrastructure.CustomerRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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