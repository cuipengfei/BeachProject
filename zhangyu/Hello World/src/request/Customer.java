package request;

import handle.Account;

import java.util.Date;

/**
 * Created by yuzhang on 8/11/15.
 */
public class Customer {
    private String nickname;
    private Date dateOFBirth;
    private Account myAccount;
    private String emailAddress;
    private MailBox myMailBox;

    public Customer(String nickname, Date dateOFBirth) {
        this.nickname = nickname;
        this.dateOFBirth = dateOFBirth;
        this.emailAddress = nickname+"@bank.com";
    }

    public String getNickname() {return nickname;}

    public Date getDateOFBirth() {return dateOFBirth;}

    public Account getMyAccount() {return myAccount;}

    public void setMyAccount(Account myAccount) {this.myAccount = myAccount;}

    public String getEmailAddress() {return emailAddress;}

    public MailBox getMyMailBox() {return myMailBox;}

    public void setMyMailBox(MailBox myMailBox) {this.myMailBox = myMailBox;}
}

