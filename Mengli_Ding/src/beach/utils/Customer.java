package beach.utils;

import com.sun.org.apache.bcel.internal.generic.NEW;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mlding on 8/11/15.
 */
public class Customer {

    private static final Customer invalidCustomer = null;

    private String nickname;
    private Date birth;
    private Account account = new Account();

    public Customer(String nickname,String birth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.nickname = nickname;
        this.birth = sdf.parse(birth);
    }
    public Account getAccount() {return account;}
    public void setAccount(Account account) {
        this.account = account;
    }

    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirth() {return birth;}
    public void setBirth(Date birth) {this.birth = birth;}

    public static boolean IsNamelegal(String name){
        if (name == null) return false;
        return name.matches("^[a-z0-9]+$");
    }

    public static Customer invalidCustomer(){
        return invalidCustomer;
    }

    public static Customer createValidCustomer(String nickname, String birth) throws ParseException {
        if (IsNamelegal(nickname))
            return new Customer(nickname,birth);
        else
            return invalidCustomer;
    }
}
