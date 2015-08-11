import java.text.ParseException;

/**
 * Created by zhenliu on 8/11/15.
 */
public class MainTest {
    public static void main(String[] args) throws ParseException {

        Customer customer1 = new Customer("1liuzhen","1990-09-04");
        Customer customer2 = new Customer("!liuzhen","1990-09-04");
        Customer customer3 = new Customer("1liuzhen","1990-09-04");

        Bank bank = new Bank();

        bank.add(customer1);
        bank.add(customer2);
        bank.add(customer3);

    }
}
