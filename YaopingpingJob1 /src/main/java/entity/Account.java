package entity;

import exception.OverdrawException;

public class Account {
    private String accountName;
    private double balance;
    private double overdraftLimit;
    private boolean overdraftAllowed;

    public Account() {
        this.balance = 0.0;
        this.overdraftLimit = 0.0;
        this.accountName = "current";
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }

    public boolean isOverdraftAllowed() {
        return overdraftAllowed;
    }

    public void setOverdraftAllowed(boolean overdraftAllowed) {
        this.overdraftAllowed = overdraftAllowed;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public double addBalance(double amount) {
        return balance += amount;
    }

    public double minusBalance(double amount) {
        return balance -= amount;
    }

    public void transferBalance(Account receiveAccount, double amount) throws OverdrawException {
        if (this.getBalance() - amount >= 0) {
            this.minusBalance(amount);
            receiveAccount.addBalance(amount);
        } else {
            throw new OverdrawException("the account's balance is insufficient");
        }
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
