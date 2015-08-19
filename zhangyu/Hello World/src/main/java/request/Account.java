package request;

/**
 * Created by yuzhang on 8/12/15.
 */
public class Account {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public int add(int num){
        this.balance += num;
        return this.balance;
    }

    public int sub(int num) throws Exception {
        this.balance -= num;
        return this.balance;
    }

}
