package entity;

import exception.AccountNameRepeatedException;
import exception.AccountNotExistException;

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
        Account account = Account.createAccount("current");
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

    public Account openAccount(String accountName) throws AccountNameRepeatedException, AccountNotExistException {

        if (isAccountNameRepeated(accountName) && accountName != null) {
            Account account = Account.createAccount(accountName);
            if (account != null) {
                accounts.add(account);
                return account;
            }
        }
        throw new AccountNotExistException();
    }

    public Account findAccountByName(String name) {
        for (int index = 0; index < accounts.size(); index++) {
            if (accounts.get(index).getAccountName().equals(name))
                return accounts.get(index);
        }
        return null;
    }

    public void displayAllAccountsStatements() {
        for (Account account : accounts) {
            String statement = String.format("%s    $%s", account.getAccountName(), account.getBalance());
            System.out.println(statement);
        }
    }

    private boolean isAccountNameRepeated(String accountName) throws AccountNameRepeatedException {
        boolean isExisted = false;
        for (Account account : accounts) {
            if (account.getAccountName().equals(accountName))
                throw new AccountNameRepeatedException();
            else {
                isExisted = true;
            }
        }
        return isExisted;
    }
}