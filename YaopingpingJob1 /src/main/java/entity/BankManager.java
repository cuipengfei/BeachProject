package entity;

import java.util.Calendar;
import java.util.Date;
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

    public static boolean calInterval(Customer customer) {
        int interval = 0;
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int Years = now.get(Calendar.YEAR);
        int Months = now.get(Calendar.MONTH);
        int Days = now.get(Calendar.DAY_OF_YEAR);

        Calendar joinBankDay = customer.getJoinBankDay();
        int joinYears = joinBankDay.get(Calendar.YEAR);
        int joinMonths = joinBankDay.get(Calendar.MONTH);
        int joinDays = joinBankDay.get(Calendar.DAY_OF_YEAR);

        interval = Years - joinYears;
        if (Months < joinMonths) {
            --interval;
        }
        if (interval >= 2)
            return true;
        else
            return false;
    }
}
