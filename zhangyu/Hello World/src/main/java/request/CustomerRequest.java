package request;

/**
 * Created by yuzhang on 8/14/15.
 */
public class CustomerRequest {
    private Customer customer;
    private Type type;
    private int num;

    public int getNum() {
        return num;
    }

    public CustomerRequest(Customer customer, Type type, int num) {
        this.customer = customer;
        this.type = type;
        this.num = num;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Type getType() {return type;}
}
