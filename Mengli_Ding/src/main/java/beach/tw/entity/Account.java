package beach.tw.entity;

/**
 * Created by mlding on 8/16/15.
 */
public class Account {
    private int money;

    private int limit;

    public int getLimit() { return limit; }

    public void setLimit(int limit) { this.limit = limit; }

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
