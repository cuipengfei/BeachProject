package beach.utils;

/**
 * Created by mlding on 8/12/15.
 */
public class Account {
    private Customer customer;
    private int money;

    public Account(int money) {
        this.money = money;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
