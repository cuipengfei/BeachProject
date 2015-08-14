package main.java.com.thoughtworks;


public class CustomerRequest {
    private Customer customer;
    private RequestType requestType;
    private double balance;

    public Customer getCustomer() {
        return customer;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public double getBalance() {
        return balance;
    }

    public CustomerRequest(Customer customer, RequestType requestType, double balance) {

        this.customer = customer;
        this.requestType = requestType;
        this.balance = balance;
    }

    public static CustomerRequest withdraw(Customer customer, double balance) {
        return  new CustomerRequest(customer,RequestType.Withdraw,balance);
    }
    public static CustomerRequest deposit(Customer customer, double balance) {
        return  new CustomerRequest(customer,RequestType.Deposit,balance);
    }
}
