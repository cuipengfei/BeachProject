package beach.tw.external;

/**
 * Created by mlding on 8/18/15.
 */
public class StandardMessageGateway implements MessageGateway {
    @Override
    public void sendMail(String address, String content) {
        System.out.println("standard message gateway");
    }
}
