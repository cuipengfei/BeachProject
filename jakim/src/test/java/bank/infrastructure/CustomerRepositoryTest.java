package bank.infrastructure;

import bank.domain.aggregator.Customer;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
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
        assertThat(customer.getJoinDate(), isToday());
    }

    @Test
    public void should_get_customer_by_nick_name() throws Exception {
        CustomerRepository repository = new CustomerRepository();
        assertFalse(repository.findByNickname("nickname").isPresent());

        repository.save(new Customer("jakim", new Date()));

        assertTrue(repository.findByNickname("jakim").isPresent());
    }

    private Matcher<Date> isToday() {
        return new BaseMatcher<Date>() {
            @Override
            public boolean matches(Object o) {
                return today().getDate() == ((Date)o).getDate();
            }

            @Override
            public void describeTo(Description description) {

            }

            private Date today() {
                return new Date();
            }
        };
    }
}