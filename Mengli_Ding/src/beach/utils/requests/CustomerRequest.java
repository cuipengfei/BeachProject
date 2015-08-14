package beach.utils.requests;

import beach.utils.Customer;

/**
 * Created by mlding on 8/14/15.
 */
public class CustomerRequest {
    private Customer customer;
    private final RequestType type;
    private final int bill;

    public CustomerRequest(int bill, Customer customer, RequestType type) {
        this.bill = bill;
        this.customer = customer;
        this.type = type;
    }

    public int getBill() {
        return bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public RequestType getType() {
        return type;
    }

    public static CustomerRequest withdraw(Customer customer,int bill){
        return new CustomerRequest(bill,customer,RequestType.WithDraw);
    }

    public static CustomerRequest deposit(Customer customer,int bill){
        return new CustomerRequest(bill,customer,RequestType.Deposit);
    }
}
