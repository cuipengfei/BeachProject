package com.second.job.tw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ppyao on 8/12/15.
 */
public class Customer {
    private String nickname;
    private Date dateofBirth1;
    private final Account account = new Account();

    public Customer(String nickname, Date dateofBirth) {
        this.nickname = nickname;
        this.dateofBirth1 = dateofBirth;

    }

    public String getNickname() {
        return nickname;
    }

    public Date getDateofBirth() {
        return dateofBirth1;
    }
    public Account getAccount() {
        return account;
    }

}
