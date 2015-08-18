package bank.infrastructure;

import bank.domain.aggregator.Customer;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CustomerRepository {
    private Set<Customer> customers = new HashSet<>();

    public boolean save(Customer customer) {
        return customers.add(customer);
    }

    public Optional<Customer> findByNickname(final String nickname) {
        return customers.stream()
                .filter(customer -> customer.nickname().equals(nickname))
                .findFirst();
    }
}
