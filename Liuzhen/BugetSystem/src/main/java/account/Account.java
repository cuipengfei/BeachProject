package account;

/**
 * Created by zhenliu on 8/24/15.
 */
public class Account {
    private String name ;
    private double balance = 0.0;

    public Account(){
        this.name = "current";
    }

    public Account(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }
}
