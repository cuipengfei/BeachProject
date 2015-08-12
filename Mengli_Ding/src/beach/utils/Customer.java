package beach.utils;

import beach.exception.MyException;
import java.util.Date;

/**
 * Created by mlding on 8/11/15.
 */
public class Customer {

    private String nickname;
    private Date birth;
    private Account account;

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Customer(String nickname,Date birth) {
        this.nickname = nickname;
        this.birth = birth;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public int withdrawMoney(int currentmoney,int money) {
        if (currentmoney>=money) currentmoney-=money;
        else {
            try {
                throw new MyException();
            } catch (MyException e) {
            }
        }
        return currentmoney;
    }
    public int depositMoney(int currentmoney,int money){
        currentmoney+=money;
        return currentmoney;
    }
}
