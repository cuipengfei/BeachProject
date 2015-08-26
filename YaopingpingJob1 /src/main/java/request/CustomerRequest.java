package request;

import entity.Account;
import entity.Customer;

public class CustomerRequest {
    private Customer customer;
    private RequestType type;
    private double amount;
    private String accountName;
    private Account fromAccount;
    private Account toAccount;

    private CustomerRequest(Customer customer, RequestType type, double amount, String accountName) {
        this.customer = customer;
        this.type = type;
        this.amount = amount;
        this.accountName = accountName;
    }

    private CustomerRequest(Account fromAccount, Account toAccount, double amount, RequestType type) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public RequestType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public static CustomerRequest depositRequest(Customer customer, double money, String accountName) {
        return new CustomerRequest(customer, RequestType.depositMoney, money, accountName);
    }

    public static CustomerRequest withdrawRequest(Customer customer, double money, String accountName) {
        return new CustomerRequest(customer, RequestType.withdraw, money, accountName);
    }

    public static CustomerRequest transferRequest(Account fromAccount, Account toAccount, double amount) {
        return new CustomerRequest(fromAccount, toAccount, amount, RequestType.transfer);
    }
}
