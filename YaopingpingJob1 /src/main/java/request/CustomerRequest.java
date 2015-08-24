package request;

import entity.Customer;

public class CustomerRequest {
    private Customer customer;
    private RequestType type;
    private double amount;

    private CustomerRequest(Customer customer, RequestType type, double amount) {
        this.customer = customer;
        this.type = type;
        this.amount = amount;
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

    public static CustomerRequest depositRequest(Customer customer, double money) {
        return new CustomerRequest(customer, RequestType.depositMoney, money);
    }

    public static CustomerRequest withdrawRequest(Customer customer, double money) {
        return new CustomerRequest(customer, RequestType.withdraw, money);
    }
}
