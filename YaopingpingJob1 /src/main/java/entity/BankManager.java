package entity;

import java.util.LinkedList;

/**
 * Created by ppyao on 8/17/15.
 */
public class BankManager {
    private String emailAddress;
    private LinkedList<Customer> PrminumCustomerList = new LinkedList<Customer>();

    public BankManager() {
        this.emailAddress = "manager@thebank.com";
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LinkedList<Customer> getPrminumCustomerList() {
        return PrminumCustomerList;
    }

}
