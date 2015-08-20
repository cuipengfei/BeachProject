package beach.tw.requests;

import beach.tw.entity.Customer;

/**
 * Created by mlding on 8/16/15.
 */
public class CustomerRequest {
    private Customer customer;
    private final RequestType type;
    private final int bill;


    public CustomerRequest(Customer customer, RequestType type, int bill) {
        this.customer = customer;
        this.type = type;
        this.bill = bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getBill() {
        return bill;
    }

    public RequestType getType() {
        return type;
    }

    public static CustomerRequest withdraw(Customer customer, int bill){
        return new CustomerRequest(customer, RequestType.withdraw, bill);
    }

    public static CustomerRequest deposit(Customer customer, int bill){
        return new CustomerRequest(customer, RequestType.deposit, bill);
    }
}
