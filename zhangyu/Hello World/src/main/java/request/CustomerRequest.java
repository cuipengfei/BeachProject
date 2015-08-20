package request;

public class CustomerRequest {
    private Customer customer;
    private Type type;
    private int num;

    public CustomerRequest(Customer customer, Type type, int num) {
        this.customer = customer;
        this.type = type;
        this.num = num;
    }

    public Customer getCustomer() {return customer;}

    public Type getType() {return type;}

    public int getNum() {return num;}

    public static CustomerRequest deposit(Customer customer, int num) {
        return new CustomerRequest(customer, Type.deposit, num);
    }

    public static CustomerRequest withdraw(Customer customer, int num) {
        return new CustomerRequest(customer, Type.withdraw, num);
    }
}
