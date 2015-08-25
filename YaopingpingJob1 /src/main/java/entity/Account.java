package entity;

public class Account {
    private String accountName;
    private double balance;
    private double overdraftLimit;
    private boolean overdraftAllowed;
    private static Account account;

    private Account() {
        this.balance = 0.0;
        this.overdraftLimit = 0.0;
        this.accountName = "current";
    }

    private Account(String accountName) {
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

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double minusBalance(double amount) {
        return balance -= amount;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public static Account createAccount(String accountName)
    {
        account=new Account(accountName);
        return account;
    }

}
