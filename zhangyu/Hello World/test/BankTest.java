import request.Type;
import org.junit.Test;
import request.Bank;
import request.Customer;
import request.CustomerRequest;
import java.text.SimpleDateFormat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yuzhang on 8/14/15.
 */
public class BankTest {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void should_balance_equals_300_when_deposit_300() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank1 = new Bank();
        bank1.addToBank(customer1);

        CustomerRequest request1 = new CustomerRequest(customer1, Type.deposit,300);
        bank1.handleRequest(request1);
    }

    @Test
    public void should_balance_equals_200_when_withdraw_100() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank1 = new Bank();
        bank1.addToBank(customer1);

        CustomerRequest request1 = new CustomerRequest(customer1, Type.deposit,300);
        bank1.handleRequest(request1);
        CustomerRequest request2 = new CustomerRequest(customer1, Type.withdraw,100);
        assertThat(200, is(bank1.handleRequest(request2)));
    }

    @Test(expected = Exception.class)
    public void should_throw_exception_when_overdraw() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank1 = new Bank();
        bank1.addToBank(customer1);

        CustomerRequest request1 = new CustomerRequest(customer1, Type.deposit,300);
        bank1.handleRequest(request1);
        CustomerRequest request2 = new CustomerRequest(customer1, Type.withdraw,301);
        bank1.handleRequest(request2);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_when_the_no_added_customer_deposit() throws Exception {
        Customer customer1 = new Customer("zhangyu", sdf.parse("2015-08-11"));
        Bank bank1 = new Bank();

        CustomerRequest request1 = new CustomerRequest(customer1, Type.deposit,300);
        bank1.handleRequest(request1);
    }
}