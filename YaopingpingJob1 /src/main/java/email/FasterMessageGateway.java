package email;

/**
 * Created by ppyao on 8/18/15.
 */
public class FasterMessageGateway implements MessageGateway {
    @Override
    public void sendEmail(String emailAddress, String message) {
        System.out.println("I am faster email");
    }
}
