package email;

public class MailSender extends MessageGateway {
    private  MailSendStatus status;
    @Override
    public MailSendStatus sendEmail(String emailAddress, String message) {

        return  MailSendStatus.OK;
    }
}
