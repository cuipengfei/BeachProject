package bank.infrastructure;

import bank.domain.aggregator.Customer;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerRepositoryTest {
    @Test
    public void should_save_customer() throws Exception {
        CustomerRepository repository = new CustomerRepository();
        Customer customer = new Customer("jakim", new Date());

        boolean result = repository.save(customer);

        assertTrue(result);
    }

    @Test
    public void should_get_customer_by_nick_name() throws Exception {
        CustomerRepository repository = new CustomerRepository();
        assertFalse(repository.findByNickname("nickname").isPresent());

        repository.save(new Customer("jakim", new Date()));

        assertTrue(repository.findByNickname("jakim").isPresent());
    }
}