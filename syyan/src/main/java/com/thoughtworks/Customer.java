package com.thoughtworks;

import java.util.*;


public class Customer {
    private final String nickName;
    private final Date dateOfBirth;
    private double balance;
    private boolean isPremiumCustomer = false;
    private Date dateOfJoin;

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public Customer(String nickName, Date dateOfBirth) {
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public void setPremiumCustomer(boolean isPremiumCustomer) {
        this.isPremiumCustomer = isPremiumCustomer;
    }

    public boolean isPremiumCustomer() {
        return isPremiumCustomer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {this.balance = balance;}

    public String getNickName() {
        return nickName;
    }

}
