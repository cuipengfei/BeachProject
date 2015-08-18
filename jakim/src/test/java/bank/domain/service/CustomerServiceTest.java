package bank.domain.service;

import bank.domain.aggregator.Customer;
import bank.infrastructure.CustomerRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerRepository = new CustomerRepository();
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void customer_service_should_add_customer() throws Exception {
        Customer customer = new Customer("jakim", new Date());

        boolean result = customerService.addCustomer(customer);

        assertTrue(result);
        assertTrue(customerRepository.findByNickname("jakim").isPresent());
    }

    @Test
    public void should_not_add_customer_when_customer_already_exist_with_nickname() throws Exception {
        Customer customer = new Customer("jakim", new Date());
        customerRepository.save(customer);

        assertTrue(customerRepository.findByNickname("jakim").isPresent());

        Customer sameCustomer = new Customer("jakim", new Date());

        assertFalse(customerService.addCustomer(sameCustomer));
    }
}