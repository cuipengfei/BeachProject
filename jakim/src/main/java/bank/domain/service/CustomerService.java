package bank.domain.service;

import bank.domain.aggregator.Customer;
import bank.repository.CustomerRepository;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean addCustomer(Customer customer) {
        return !isExist(customer) && customerRepository.save(customer);
    }

    private boolean isExist(Customer customer) {
        return customerRepository.findByNickname(customer.nickname()).isPresent();
    }
}
