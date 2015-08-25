package request;
import account.Account;
import customer.*;

public class CustomerRequest {
    private Customer customer;
    private String accountName;
    private RequestType requestType;
    private double money;
    private Account accountTransferFrom;
    private Account accountTransferTo;

    private CustomerRequest(Customer customer, String accountName, RequestType requestType, double money) {
        this.customer = customer;
        this.accountName = accountName;
        this.requestType = requestType;
        this.money = money;
    }

    private CustomerRequest(Account accountTransferFrom, Account accountTransferTo, RequestType requestType, double money) {
        this.accountTransferFrom = accountTransferFrom;
        this.accountTransferTo = accountTransferTo;
        this.requestType = requestType;
        this.money = money;
    }

    public Account getAccountTransferFrom() {
        return accountTransferFrom;
    }

    public Account getAccountTransferTo() {
        return accountTransferTo;
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

    public static CustomerRequest transfer(Account accountTransferFrom, Account accountTransferTo, RequestType requestType, double money){
        return new CustomerRequest(accountTransferFrom,accountTransferTo,RequestType.transfer,money);
    }
    public String getAccountName() {
        return accountName;
    }
}
