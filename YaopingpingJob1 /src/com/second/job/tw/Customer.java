package com.second.job.tw;

import java.util.Date;

/**
 * Created by ppyao on 8/12/15.
 */
public class Customer {

    private String nickname;
    private Date dateofBirth;
    private final Account account = new Account();
    private String emailAddress;
    private String message;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getDateofBirth1() {
        return dateofBirth;
    }

    public String getNickname() {
        return nickname;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public Account getAccount() {
        return account;
    }

    public Customer(String nickname, Date dateofBirth) {
        this.nickname = nickname;
        this.dateofBirth = dateofBirth;
        this.emailAddress = nickname + "@bank.com";

    }

    public void setMessage(String message) {
        this.message = message;
    }
}
