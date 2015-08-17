package beach.utils;

/**
 * Created by mlding on 8/17/15.
 */
public class EmailSender {
    public static void sendMail(Customer customer) {
        customer.getEmail().setAddress(customer.getName() + "@thebank.com");
        customer.getEmail().setContent("Dear " + customer.getName() + ", Welcome to the Bank");
    }
}
