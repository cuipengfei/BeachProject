import java.util.Date;

/**
 * Created by yuzhang on 8/11/15.
 */
public class Customer {
    private String nickname;
    private Date dateOFBirth;
    private Account myAccount;

    public Customer(String nickname, Date dateOFBirth) {
        this.nickname = nickname;
        this.dateOFBirth = dateOFBirth;
    }

    public String getNickname() {return nickname;}

    public Date getDateOFBirth() {return dateOFBirth;}

    public Account getMyAccount() {return myAccount;}

    public void setMyAccount(Account myAccount) {
        this.myAccount = myAccount;
    }

}

