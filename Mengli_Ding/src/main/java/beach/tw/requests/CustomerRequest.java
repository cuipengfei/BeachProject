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
    private String accountName;

    private CustomerRequest(Customer customer, Account accountOut, Account accountIn, RequestType type, int bill) {
        this.customer = customer;
        this.accountOut = accountOut;
        this.accountIn = accountIn;
        this.type = type;
        this.bill = bill;
    }

    private CustomerRequest(Customer customer, RequestType type, int bill, String accountName) {
        this.customer = customer;
        this.type = type;
        this.bill = bill;
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
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

    public static CustomerRequest withdraw(Customer customer, int bill, String accountName) {
        return new CustomerRequest(customer, RequestType.withdraw, bill, accountName);
    }

    public static CustomerRequest deposit(Customer customer, int bill, String accountName) {
        return new CustomerRequest(customer, RequestType.deposit, bill, accountName);
    }

    public static CustomerRequest transfer(Customer fromCustomer, Account fromAccount, Account toAccount, int bill) {
        return new CustomerRequest(fromCustomer, fromAccount, toAccount, RequestType.transfer, bill);
    }
}
