package entity;

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

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public double addBalance(double money) {
        return balance += money;
    }

    public double minusBalance(double money) {
        return balance -= money;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
