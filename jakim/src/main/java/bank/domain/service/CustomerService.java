package bank.domain.service;

import bank.domain.aggregator.Customer;
import bank.infrastructure.CustomerRepository;

import static bank.domain.event.Event.customerAddedEvent;
import static bank.domain.event.Event.fire;

public class CustomerService {
    private CustomerRepository Repository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean addCustomer(Customer customer) {
        if (!isExist(customer) && customerRepository.save(customer)) {
            fire(customerAddedEvent(customer));
            return true;
        }
        return false;
    }

    private boolean isExist(Customer customer) {
        return customerRepository.findByNickname(customer.nickname()).isPresent();
    }
}
