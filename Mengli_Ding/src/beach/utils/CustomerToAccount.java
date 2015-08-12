package beach.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mlding on 8/12/15.
 */
public class CustomerToAccount {
//    public static int currentmoney = 0;
    public static void main(String args[]) throws ParseException {
        int currentMoney, temp1, temp2, temp3;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer1 = new Customer("mike",sdf.parse("1993-08-09"));
        Account account1 = new Account(5);
        Bank bank = new Bank();
        if (bank.IsValidCustomer(customer1)) {
            System.out.println("The customer is valid");
            customer1.setAccount(account1);
            account1.setCustomer(customer1);
            currentMoney = customer1.getAccount().getMoney();
            temp1 = customer1.withdrawMoney(currentMoney, 6);
            System.out.println("The remaining money: " + temp1);
            temp2 = customer1.withdrawMoney(temp1, 5);
            System.out.println("The remaining money: " + temp2);
            temp3 = customer1.depositMoney(temp2, 2);
            System.out.println("The remaining money: " + temp3);

        }
        else{
            System.out.println("The customer is invalid");
        }
    }
}
