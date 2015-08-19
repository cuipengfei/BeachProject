package entity;

/**
 * Created by ppyao on 8/12/15.
 */
public class Account {

    private double balance = 0.0;

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

    public double deposit(double amount) {
        // add transaciton history
        return balance;
    }

    public double withdraw(double amount) {
        // add transaction history
        if (balance < amount) {
            throw new RuntimeException();
        }
        return balance;
    }

    class DepositRequest {

    }

    class WithdrawRequest {

    }
}
