package com.tw.beach.entity;

import java.util.Date;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Customer {
    private static final Customer invalidCustomer = new Customer(null, null);

    private final String nickName;
    private final Date birthDay;

    private Customer(String nickName, Date birthDay) {
        this.nickName = nickName;
        this.birthDay = birthDay;
    }

    public String nickName() {
        return nickName;
    }

    public static Customer invalidCustomer() {
        return invalidCustomer;
    }

    public static Customer createCustomer(String nickName, Date date) {
        if (isNickNameValid(nickName)) {
            return new Customer(nickName, date);
        } else {
            return invalidCustomer;
        }
    }

    private static Boolean isNickNameValid(String nickName) {
        Pattern validNamePattern = compile("^[a-z0-9]+$");
        return validNamePattern.matcher(nickName).matches();
    }

}
