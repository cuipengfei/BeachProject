package request;

import entity.Customer;

public class CustomerRequest {
    private Customer customer;
    private RequestType type;
    private double amount;
    private String accountName;

    private CustomerRequest(Customer customer, RequestType type, double amount, String accountName) {
        this.customer = customer;
        this.type = type;
        this.amount = amount;
        this.accountName = accountName;
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

    public static CustomerRequest depositRequest(Customer customer, double money, String accountName) {
        return new CustomerRequest(customer, RequestType.depositMoney, money, accountName);
    }

    public static CustomerRequest withdrawRequest(Customer customer, double money, String accountName) {
        return new CustomerRequest(customer, RequestType.withdraw, money, accountName);
    }
}
