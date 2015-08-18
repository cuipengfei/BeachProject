package beach.utils;

/**
 * Created by mlding on 8/17/15.
 */
public class EmailSender {
    public void sendMail(Customer customer) {
        customer.getEmail().setAddress(customer.getName() + "@thebank.com");
        customer.getEmail().setContent("Dear " + customer.getName() + ", Welcome to the Bank");
    }

    public void sendMailToManager(BankManager bankManager, Customer customer){
        bankManager.getEmail().setAddress("manager@thebank.com");
        bankManager.getEmail().setContent(customer.getName() + " is now a premium customer");
    }
}
