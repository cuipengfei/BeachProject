import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuzhang on 8/11/15.
 */
public class CustomerTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Customer customer2 = new Customer("zhangyu", sdf.parse("2015-08-12"));
        Customer customer3 = new Customer("ZhangYu", sdf.parse("2015-08-13"));
        Customer customer4 = new Customer("zhangyu4", sdf.parse("2015-08-13"));

        Bank bank1 = new Bank();

        bank1.addToBank(customer1);
        bank1.addToBank(customer2);
        bank1.addToBank(customer3);

        customer1.getMyAccount().deposit(300);

        try {
            customer4.getMyAccount().deposit(300);
        }catch (NullPointerException e){
            System.out.println("you are not added to bank");
        }

        try {
            customer1.getMyAccount().withdraw(301);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

