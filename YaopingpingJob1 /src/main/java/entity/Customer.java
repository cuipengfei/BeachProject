package entity;

import java.util.Calendar;

public class Customer {
    private Calendar joinBankDay;
    private String nickname;
    private Calendar dateOfBirth;
    private Account account;
    private String emailAddress;
    private boolean premiumDefault;
    private boolean acceptReward;
    private boolean overdraftAllowed;

    public Calendar getJoinBankDay() {
        return joinBankDay;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getNickname() {
        return nickname;
    }

    public Account getAccount() {
        return account;
    }

    public boolean isPremiumDefault() {
        return premiumDefault;
    }

    public void setPremiumDefault(boolean premiumDefault) {
        this.premiumDefault = premiumDefault;
    }

    public Customer(String nickname, Calendar dateOfBirth) {
        if (!isValidName(nickname)) {
            throw new IllegalArgumentException();
        }
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = nickname + "@bank.com";
        account = new Account();
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

    private boolean isValidName(String nickname) {
        return nickname.matches("^[a-z0-9]+$");
    }
}