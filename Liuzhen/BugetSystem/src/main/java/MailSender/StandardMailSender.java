package mailsender;
public class StandardMailSender implements MailSender {
    public MailSenderStatusType status = MailSenderStatusType.OK;

    @Override
    public void sendEmail(String receiverAddress,String mailContent){
        System.out.println(mailContent);
    }

    @Override
    public MailSenderStatusType getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(MailSenderStatusType status) {
        this.status = status;
    }
}
