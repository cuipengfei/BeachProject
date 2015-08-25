package email;

public class FasterMessageGateway extends MessageGateway {
    @Override
    public MailSendStatus sendEmail(String emailAddress, String message) {
        return MailSendStatus.OK;
    }
}
