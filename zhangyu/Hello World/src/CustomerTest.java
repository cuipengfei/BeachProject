import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuzhang on 8/11/15.
 */
public class CustomerTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Customer customer1 = new Customer("zhangyu",sdf.parse("2015-08-11"));
        Customer customer2 = new Customer("zhangyu",sdf.parse("2015-08-12"));
        Customer customer3 = new Customer("ZhangYu",sdf.parse("2015-08-13"));

        Bank bank = new Bank();

        bank.addToBank(customer1);
        bank.addToBank(customer2);
        bank.addToBank(customer3);
    }
}
