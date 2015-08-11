package beach.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlding on 8/11/15.
 */
public class Bank {

    public static boolean IsNamelegal(String name){

        if (name == null)
            return false;

        return name.matches("^[a-z0-9]+$");
    }

    static List list = new ArrayList();

    public static boolean IsNameRepeat(Customer customer){
        boolean Namerepeat = false;

        int length = 0;
        for (int i=0;i<=list.size()-1;i++){
            if (! customer.equals(list.get(i)))
                length++;
        }



        if (length == list.size())
            Namerepeat = true;

        return Namerepeat;
    }

    public void IsAdd(Customer customer) {

        boolean Namelegal = IsNamelegal(customer.getNickname());
        boolean repeat = IsNameRepeat(customer);

        System.out.print("aa "+Namelegal+"bb "+repeat);

        if (Namelegal && repeat) {
            list.add(customer);
            for (int i=0;i<=list.size()-1;i++){
                System.out.print(list.get(i));
            }

        } else {
            System.out.println("Customer can not be added to bank!");
        }
    }

    }

