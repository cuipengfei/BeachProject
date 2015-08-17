package main.java.com.thoughtworks;

import java.util.*;


public class Customer {
    private final String nickName;
    private final Date dateOfBirth;
    private double balance;

    public Customer(String nickName, Date dateOfBirth) {
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNickName() {return nickName;}

}
