package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Customer {
    private Calendar joinBankDay;
    private String nickname;
    private Calendar dateOfBirth;
    private Account account;
    private String emailAddress;
    private boolean premiumDefault;
    private boolean acceptReward;
    private List<Account> accounts = new ArrayList<Account>();

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
        accounts.add(account);
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

    private boolean isValidName(String nickname) {
        return nickname.matches("^[a-z0-9]+$");
    }

    public double calculate() {
        double totalBalance = 0d;
        if (accounts.iterator().hasNext()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public Account createAccount(String accountName) {
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName))
                return null;
        }
        return new Account(accountName);
    }

}