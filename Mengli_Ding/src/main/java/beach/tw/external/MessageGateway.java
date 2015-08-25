package beach.tw.external;

/**
 * Created by mlding on 8/18/15.
 */
public interface MessageGateway {
    Status sendMail(String address, String content);
}
