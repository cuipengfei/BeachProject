package bank.infrastructure;

import bank.domain.aggregator.Customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CustomerRepository {
    private Set<Customer> customers = new HashSet<>();

    public boolean save(Customer customer) {
        customer.setJoinDate(today());
        return customers.add(customer);
    }

    public Optional<Customer> findByNickname(final String nickname) {
        return customers.stream()
                .filter(customer -> customer.nickname().equals(nickname))
                .findFirst();
    }

    private Date today() {
        return new Date();
    }
}
