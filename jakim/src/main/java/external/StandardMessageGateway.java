package external;

public class StandardMessageGateway implements MessageGateway{

    @Override
    public void sendMessage(String email, String message) {
        System.out.println(String.format("Message: '%s' was sent to %s", message, email));
    }
}
