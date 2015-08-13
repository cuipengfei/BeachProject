import java.text.ParseException;
import java.util.Date;

/**
 * Created by zhenliu on 8/11/15.
 */
public class MainTest {
    public static void main(String[] args) throws Exception {

        Customer customer1 = Customer.createCustomer("1liuzhen", new Date());
        Customer customer2 = Customer.createCustomer("!liuzhen", new Date());
        Customer customer3 = Customer.createCustomer("1liuzhen", new Date());

        Bank bank1 = new Bank();

        bank1.add(customer1);
        bank1.add(customer2);
        bank1.add(customer3);

        try{
            bank1.deposit(customer1, 1000.0);
        }catch (Exception e)
        {
            System.out.println("Throw Exception:"+e);
        }
        System.out.println(customer1.getAccount());

        try{
            bank1.withdraw(customer1, 100.0);
        }catch (Exception e)
        {
            System.out.println("Throw Exception:"+e);
        }
        System.out.println(customer1.getAccount());

        try{
            bank1.withdrawAll(customer1);
        }catch (Exception e)
        {
            System.out.println("Throw Exception:"+e);
        }
        System.out.println(customer1.getAccount());

        try{
            bank1.withdraw(customer1, 100.0);
        }catch (Exception e)
        {
            System.out.println("Throw Exception:"+e);
        }
        System.out.println(customer1.getAccount());

    }
}
