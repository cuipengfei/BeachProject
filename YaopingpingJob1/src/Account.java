import java.io.IOError;
import java.io.IOException;

/**
 * Created by ppyao on 8/11/15.
 */
public class Account {
    private double balance;
    private Customer customer;
    public Account(Customer customer,double balance)
    {
        this.customer=customer;
        this.balance=balance;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    public double getBalance()
    {
        return balance;
    }


}
