package bank.domain.service;

import bank.domain.aggregator.Account;
import bank.domain.aggregator.Customer;
import bank.domain.exception.OverdrawException;
import bank.infrastructure.CustomerRepository;

import static bank.domain.event.Event.fire;
import static bank.domain.event.Event.newPremiumEvent;

public class BankingService {
    private CustomerRepository customerRepository;

    public BankingService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public float deposit(Customer customer, float amount) {
        float balance = getAccount(customer).add(amount);
        if (newPremium(customer, balance)) {
            customer.setPremium(true);
            fire(newPremiumEvent(customer));
        }
        return balance;
    }

    private boolean newPremium(Customer customer, float balance) {
        return balance >= 40000 && !customer.isPremium();
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
