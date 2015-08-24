package email;

public abstract class MessageGateway {
    protected MailSendStatus sendStatus;

    public MailSendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(MailSendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public abstract void sendEmail(String emailAddress, String message);
}
