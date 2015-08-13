package beach.utils;

import beach.exception.MyException;

/**
 * Created by mlding on 8/12/15.
 */
public class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public int withdrawMoney(int bill) {
        if (money>=bill) money-=bill;
        else {
            try {
                throw new MyException();
            } catch (MyException e) {
            }
        }
        return money;
    }

    public int depositMoney(int bill){
        if(bill>0)
            money+=bill;
        return money;
    }
}
