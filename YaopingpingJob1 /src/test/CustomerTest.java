import entity.Customer;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ppyao on 8/12/15.
 */
public class CustomerTest {
    @Test
    public void defaultCustomerisAccount() {
        Calendar birthday=Calendar.getInstance();
        birthday.set(1989,4,1);
        //given
        Customer customer = new Customer("yaoping",birthday);
        //when
        assertThat(customer.getAccount().getBalance(), is(0.0));
    }


}