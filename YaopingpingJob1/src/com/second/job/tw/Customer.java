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

    // deposit money
    public double depositMoney(Account account, double money) {
        double tempBalance;
        if (money < 0) {
            System.out.println("please input the right money");
            return account.getBalance();
        } else {
            tempBalance=account.getBalance()+money;
            System.out.println("tempBalance  "+tempBalance);
            account.setBalance(tempBalance);
            return tempBalance;
        }
    }
    public void withdrawAllMoney(Account account)
    {
        try {
            account.withdrawMoney(account,account.getBalance());
            System.out.println("Are you try to withdraw your balance!");
            System.out.println(account.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //withdraw money
    public double withdrawMoney(Account account, double money) throws Exception {
        double currrentBalance=account.getBalance() - money;
        if (currrentBalance > 0||currrentBalance==0) {
            account.setBalance(currrentBalance);
            System.out.println("the balacne is  " + currrentBalance);
            return currrentBalance;
        } else throw new Exception("Your account balance is overdraw");

    }


}
