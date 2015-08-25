package email;

public abstract class MessageGateway {
    public abstract MailSendStatus sendEmail(String emailAddress, String message);
}
