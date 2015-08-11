import java.text.ParseException;

/**
 * Created by ppyao on 8/11/15.
 */
public class addCustomerTest {
    public static void main(String args[])throws ParseException
    {
        // 测试添加正确的情况
        Bank  customerAdministator=new Bank();
        Customer customer=new Customer("ni","2012-12-14");
        customerAdministator.addCustomertoBank(customer);
        Customer customer1=new Customer("NI","2012-12-14");
        customerAdministator.addCustomertoBank(customer1);

        System.out.println("tee");
        Customer customer2=new Customer("ni","2012-12-14");
        customerAdministator.addCustomertoBank(customer2);
    }
}
