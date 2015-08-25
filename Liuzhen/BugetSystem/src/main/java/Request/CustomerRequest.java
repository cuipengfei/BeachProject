package request;
import customer.*;

public class CustomerRequest {
    private Customer customer;
    private String accountName;
    private RequestType requestType;
    private double money;

    public CustomerRequest(Customer customer, String accountName, RequestType requestType, double money) {
        this.customer = customer;
        this.accountName = accountName;
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

    public static CustomerRequest withDraw(Customer customer, String accountName, double money){
        return new CustomerRequest(customer,accountName,RequestType.withDraw,money);
    }

    public static CustomerRequest deposit(Customer customer, String accountName, double money){
        return new CustomerRequest(customer,accountName, RequestType.deposit,money);
    }

    public String getAccountName() {
        return accountName;
    }
}
