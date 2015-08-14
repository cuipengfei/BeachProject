package beach.utils;

/**
 * Created by mlding on 8/12/15.
 */
public class Account {
    private int money = 5;

    public int getMoney() {return money;}

    public void addMoney(int bill){money += bill;}

    public void minusMoney(int bill){money -= bill;}
}
