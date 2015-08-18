package request;

import handle.Account;

import java.util.Date;

public class Customer {
    private String nickname;
    private Date dateOfBirth;
    private Account myAccount;
    private String emailAddress;
    private String message;
    private boolean isPremium = false;
    private Date dateOfJoin;

    public Customer(String nickname, Date dateOFBirth) {
        this.nickname = nickname;
        this.dateOfBirth = dateOFBirth;
        this.emailAddress = nickname+"@bank.com";
    }

    public String getNickname() {return nickname;}

    public Date getDateOfBirth() {return dateOfBirth;}

    public Account getMyAccount() {return myAccount;}

    public void setMyAccount(Account myAccount) {this.myAccount = myAccount;}

    public String getEmailAddress() {return emailAddress;}

    public String getMessage() {return message;}

    public boolean isPremium() {return isPremium;}

    public void setIsPremium(boolean isPremium) {this.isPremium = isPremium;}

    public Date getDateOfJoin() {return dateOfJoin;}

    public void setDateOfJoin(Date dateOfJoin) {this.dateOfJoin = dateOfJoin;}
}

