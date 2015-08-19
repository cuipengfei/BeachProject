package email;

/**
 * Created by ppyao on 8/18/15.
 */
public class MailSender implements MessageGateway {
    @Override
    public void sendEmail(String eamilAdress, String message) {
        System.out.println("I am standard message gateway");
    }
}
