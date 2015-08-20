package Request;
import  Customer.*;

public class CustomerRequest {
    private Customer customer;
    private RequestType requestType;
    private double money;

    public CustomerRequest(Customer customer, RequestType requestType, double money) {
        this.customer = customer;
        this.requestType = requestType;
        this.money = money;
    }

    public Customer getCustomer() {
        return customer;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public double getMoney() {
        return money;
    }

    public static CustomerRequest withDraw(Customer customer, double money){
        return new CustomerRequest(customer,RequestType.withDraw,money);
    }

    public static CustomerRequest deposit(Customer customer, double money){
        return new CustomerRequest(customer,RequestType.deposit,money);
    }
}
