package bank.domain.service;

import bank.domain.aggregator.Account;
import bank.domain.aggregator.Customer;
import bank.domain.exception.OverdrawException;
import bank.repository.CustomerRepository;

public class BankingService {
    private CustomerRepository customerRepository;

    public BankingService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public float deposit(Customer customer, float amount) {
        return getAccount(customer).add(amount);
    }

    public float withdraw(Customer customer, float amount) {
        Account account = getAccount(customer);
        if (overdraw(amount, account)) {
            throw new OverdrawException("Overdraw");
        }
        return account.minus(amount);
    }

    public float withdrawAll(Customer customer) {
        Account account = getAccount(customer);
        return account.minus(account.balance());
    }

    private boolean overdraw(float amount, Account account) {
        return account.balance() < amount;
    }

    private Account getAccount(Customer customer) {
        return customerRepository.findByNickname(customer.nickname()).get().getAccount();
    }
}
