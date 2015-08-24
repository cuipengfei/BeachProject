package mailsender;

public interface MailSender {
    MailSenderStatusType sendEmail(String receiverAddress, String mailContent);

    void setStatus(MailSenderStatusType status);

    MailSenderStatusType getStatus();
}
