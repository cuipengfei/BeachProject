package account;

/**
 * Created by zhenliu on 8/24/15.
 */
public class Account {
    private String name  ;
    private double balance = 0.0;
    private boolean overdraftAllowed;

    public Account(){
        this.name = "current";
    }

    public Account(String name) throws Exception {
        if (name == null){
            throw new Exception("Account name should not be null");
        }else {
            this.name = name;
        }
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

    public boolean isOverdraftAllowed() {
        return overdraftAllowed;
    }

    public void setOverdraftAllowed(boolean overdraftAllowed) {
        this.overdraftAllowed = overdraftAllowed;
    }

    public void addMoney(double money){
        balance += money;
    }

    public void minusMoney(double money){
        balance -= money;
    }
}
