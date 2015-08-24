package mailsender;

public interface MailSender {
    void sendEmail(String receiverAddress, String mailContent);

    void setStatus(MailSenderStatusType status);

    MailSenderStatusType getStatus();
}
