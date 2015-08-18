package external;

public class FasterMessageGateway implements MessageGateway{
    @Override
    public void sendMessage(String email, String message) {
        System.out.println("I am faster");
    }
}
