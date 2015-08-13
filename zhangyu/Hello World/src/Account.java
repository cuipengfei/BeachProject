/**
 * Created by yuzhang on 8/12/15.
 */
public class Account {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int deposit(int num){
        this.balance += num;
        System.out.println("[successful deposit]Your balance:" + this.balance);
        return this.balance;
    }

    public int withdraw(int num) throws Exception {
        if (num > this.balance) {
            System.out.println("[Failed withdraw]Your balance:"+this.balance);
            throw new Exception("overdraw");
        }
        this.balance -= num;
        System.out.println("[successful withdraw]Your balance:" + this.balance);
        return this.balance;
    }

}
