package entity;

import java.util.Calendar;

/**
 * Created by ppyao on 8/12/15.
 */
public class Customer {
    private Calendar joinBankDay;
    private String nickname;
    private Calendar dateofBirth;
    private final Account account = new Account();
    private String emailAddress;
    private boolean isPreminumDefault;
    private boolean acceptReward;
    private boolean overdraftAllowed;
    private double overdraftAmount;

    public Calendar getJoinBankDay() {
        return joinBankDay;
    }

    public void setIsPreminumDefault(boolean isPreminumDefault) {
        this.isPreminumDefault = isPreminumDefault;
    }

    public boolean isPreminum() {
        return isPreminumDefault;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public Calendar getDateofBirth() {
        return dateofBirth;
    }

    public Account getAccount() {
        return account;
    }

    public Customer(String nickname, Calendar dateofBirth) {
        this.nickname = nickname;
        this.dateofBirth = dateofBirth;
        this.emailAddress = nickname + "@bank.com";

    }

    public boolean isAcceptReward() {
        return acceptReward;
    }

    public void setAcceptReward(boolean acceptReward) {
        this.acceptReward = acceptReward;
    }

    public void setJoinBankDay(Calendar joinBankDay) {
        this.joinBankDay = joinBankDay;
    }

    public boolean isOverdraftAllowed() {
        return overdraftAllowed;
    }

    public void setOverdraftAllowed(boolean overdraftAllowed) {
        this.overdraftAllowed = overdraftAllowed;
    }

    public double getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(double overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }
}
