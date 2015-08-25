package beach.tw.requests;

import beach.tw.entity.Account;
import beach.tw.entity.Customer;

/**
 * Created by mlding on 8/16/15.
 */
public class CustomerRequest {
    private Customer customer;
    private Account accountOut;
    private Account accountIn;
    private final RequestType type;
    private final int bill;

    public CustomerRequest(Customer customer, RequestType type, int bill) {
        this.customer = customer;
        this.type = type;
        this.bill = bill;
    }

    public CustomerRequest(Customer customer, Account accountOut, Account accountIn, RequestType type, int bill) {
        this.customer = customer;
        this.accountOut = accountOut;
        this.accountIn = accountIn;
        this.type = type;
        this.bill = bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Account getAccountOut() {
        return accountOut;
    }

    public Account getAccountIn() {
        return accountIn;
    }

    public int getBill() {
        return bill;
    }

    public RequestType getType() {
        return type;
    }

    public static CustomerRequest withdraw(Customer customer, int bill) {
        return new CustomerRequest(customer, RequestType.withdraw, bill);
    }

    public static CustomerRequest deposit(Customer customer, int bill) {
        return new CustomerRequest(customer, RequestType.deposit, bill);
    }

    public static CustomerRequest transfer(Customer customer, Account account1, Account account2, int bill) {
        return new CustomerRequest(customer, account1, account2, RequestType.transfer, bill);
    }
}
