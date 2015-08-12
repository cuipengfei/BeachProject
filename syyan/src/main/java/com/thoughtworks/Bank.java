package main.java.com.thoughtworks;

import java.util.*;
import java.util.regex.Pattern;


public class Bank {

    public List<Customer> customerList = new ArrayList<>();

    public boolean addCustomer(Customer customer) {
        if (!contains(customer) && isValid(customer.getNickName())) {
            customerList.add(customer);
            return true;
        }
        return false;
    }

    private boolean isValid(String nickName) {
        Pattern pattern = Pattern.compile("^[a-z0-9]+$");
        return pattern.matcher(nickName).matches();
    }

    private boolean contains(Customer customer) {
        for (Customer temp : customerList) {
            if (temp.getNickName().equals(customer.getNickName())) {
                return true;
            }
        }
        return false;
    }

}
