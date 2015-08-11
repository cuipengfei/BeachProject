package beach.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlding on 8/11/15.
 */
public class Bank {

    List<Customer> customerList = new ArrayList<>();

    public boolean IsNamelegal(String name){
        if (name == null) return false;
        return name.matches("^[a-z0-9]+$");
    }


    public boolean IsNameRepeat(Customer customer) {
        for (Customer temp : customerList) {
            if (temp.getNickname().equals(customer.getNickname()))
                return true;
        }
            return false;
    }

    public void AddCustomer(Customer customer) {

        boolean Namelegal = IsNamelegal(customer.getNickname());
        boolean Isrepeat = IsNameRepeat(customer);
        if (Namelegal && !Isrepeat) {
            customerList.add(customer);
            System.out.println("Customer can be added to bank!");
        } else {
            System.out.println("Customer can not be added to bank!");
        }
    }

    }

