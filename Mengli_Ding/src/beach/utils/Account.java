package beach.utils;

/**
 * Created by mlding on 8/12/15.
 */
public class Account {
    private int money = 0;

    public int getMoney() {return money;}

    public void addMoney(int bill){
        if (bill >= 0)
            money += bill;
    }

    public void minusMoney(int bill){
        if (money >= bill)
            money -= bill;
    }
}
