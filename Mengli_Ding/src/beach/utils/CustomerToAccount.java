package beach.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mlding on 8/12/15.
 */
public class CustomerToAccount {
    public static void main(String args[]) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer1 = new Customer("mike",sdf.parse("1993-08-09"));
        Account account1 = new Account(5);
        Bank bank = new Bank();
        if (bank.IsValidCustomer(customer1)) {
            System.out.println("The customer is valid");

            customer1.setAccount(account1);
            System.out.println("The remaining money: " + customer1.getAccount().withdrawMoney(2));
            System.out.println("The remaining money: " + customer1.getAccount().withdrawMoney(5));
            System.out.println("The remaining money: " + customer1.getAccount().depositMoney(3));
        }
        else{
            System.out.println("The customer is invalid");
        }
    }
}
