import beach.utils.Bank;
import beach.utils.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by mlding on 8/12/15.
 */
public class CustomerTest {
    @Test
    public void shouldAddSuccessWhenGivenValidCustomer() throws Exception {
        //given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Customer customer = new Customer("mike",sdf.parse("1993-08-09"));

        //when
        boolean isSucess = bank.IsValidCustomer(customer);

        //then
        assertTrue(isSucess);
    }

    @Test
    public void shouldAddFailureWhenGivenInvalidCustomer() throws Exception {
        //given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Customer invalidCustomer = new Customer("Mike",sdf.parse("1993-08-09"));

        //when
        boolean isSucess = bank.IsValidCustomer(invalidCustomer);

        //then
        assertFalse(isSucess);
    }

    @Test
    public void shouldAddFailureWhenGivenSameName() throws Exception {
        //given
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bank bank = new Bank();
        Customer customer = new Customer("mike",sdf.parse("1993-08-09"));
        Customer existCustomer = new Customer("mike",sdf.parse("1993-08-09"));

        //when
        boolean isSucess = bank.addCustomer(customer);

        //then
        assertTrue(isSucess);

        //when
        boolean isSecondSucess = bank.addCustomer(existCustomer);
        assertEquals(false, isSecondSucess);
    }
}