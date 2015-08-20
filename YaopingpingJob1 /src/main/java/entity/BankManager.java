package entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ppyao on 8/17/15.
 */
public class BankManager {
    private String emailAddress;
    private List<Customer> rewardCustomerList = new LinkedList<Customer>();

    public BankManager() {
        this.emailAddress = "manager@thebank.com";
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Customer> getRewardCustomerList() {
        return rewardCustomerList;
    }

    public void setRewardCustomerList(List<Customer> rewardCustomerList) {
        this.rewardCustomerList = rewardCustomerList;
    }
}
