package main.java.com.thoughtworks;

import java.util.*;


public class Customer {
    private final String nickName;
    private final Date dateOfBirth;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNickName() {
        return nickName;
    }

    public Customer(String nickName, Date dateOfBirth) {
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
    }


}
