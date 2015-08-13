package beach.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mlding on 8/11/15.
 */
public class test {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer = new Customer("mike",sdf.parse("1993-08-09"));
        Customer customer2 = new Customer("Mike",sdf.parse("1993-08-09"));
        Customer customer3 = new Customer("mike",sdf.parse("1992-08-09"));

        Bank bank = new Bank();
        if (bank.IsValidCustomer(customer))
            System.out.println("Customer can be added to bank!");
        else
            System.out.println("Customer can not be added to bank!");
        if (bank.IsValidCustomer(customer2))
            System.out.println("Customer can be added to bank!");
        else
            System.out.println("Customer can not be added to bank!");
        if (bank.IsValidCustomer(customer3))
            System.out.println("Customer can be added to bank!");
        else
            System.out.println("Customer can not be added to bank!");

    }
}
