package email;

public class EmailSend implements MessageGateway{
    @Override
    public void sendEmail(String emailAddress, String content) {
        System.out.println(content);
    }
}
