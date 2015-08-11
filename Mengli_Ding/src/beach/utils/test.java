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
        Customer customer2 = new Customer("meng",sdf.parse("1993-08-09"));

        Bank bank = new Bank();
        bank.IsAdd(customer);
        bank.IsAdd(customer2);



    }
}
