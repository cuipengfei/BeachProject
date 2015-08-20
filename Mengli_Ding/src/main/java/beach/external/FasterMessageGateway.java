package beach.external;

/**
 * Created by mlding on 8/18/15.
 */
public class FasterMessageGateway implements MessageGateway {
    @Override
    public void sendMail(String address, String content) {
        System.out.println("faster message gateway");
    }
}
