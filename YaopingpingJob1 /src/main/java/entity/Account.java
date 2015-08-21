package entity;

/**
 * Created by ppyao on 8/12/15.
 */
public class Account {
    private double balance;
    private double overdraftLimit;

    public void setBalance(double balance) {
        this.balance = balance;
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

    public Account() {
        this.balance = 0.0;
        this.overdraftLimit = 0.0;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
