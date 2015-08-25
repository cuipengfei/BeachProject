package entity;

import exception.AccountNameRepeatedException;
import exception.OverdrawException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Customer {
    private Calendar joinBankDay;
    private String nickname;
    private Calendar dateOfBirth;
    private String emailAddress;
    private boolean premiumDefault;
    private boolean acceptReward;
    private double totalAssets;
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

    public boolean isPremiumDefault() {
        return premiumDefault;
    }

    public void setPremiumDefault(boolean premiumDefault) {
        this.premiumDefault = premiumDefault;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Customer(String nickname, Calendar dateOfBirth) {
        if (!isValidName(nickname)) {
            throw new IllegalArgumentException();
        }
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = nickname + "@bank.com";
        Account account = new Account();
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

    public double calculateTotalAssets() {
        for (Account account : accounts) {
            totalAssets += account.getBalance();
        }
        return totalAssets;
    }

    public Account createAccount(String accountName) throws AccountNameRepeatedException {
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName))
                throw new AccountNameRepeatedException();
        }
        Account account = new Account(accountName);
        accounts.add(account);
        return account;
    }

    public Account findAccountByName(String name) {
        for (int index = 0; index < accounts.size(); index++) {
            if (accounts.get(index).getAccountName().equals(name))
                return accounts.get(index);
        }
        return null;
    }

    public void transferAccount(Account transferAccount, Account receiveAccount, double transferAmount) throws OverdrawException {
        transferAccount.transferBalance(receiveAccount, transferAmount);
    }

}