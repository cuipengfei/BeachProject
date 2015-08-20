package beach.tw.entity;

/**
 * Created by mlding on 8/16/15.
 */
public class Account {
    private int money = 0;

    public int getMoney() {
        return money;
    }

    public void add(int bill){
        money += bill;
    }

    public void minus(int bill){
        money -= bill;
    }

}
