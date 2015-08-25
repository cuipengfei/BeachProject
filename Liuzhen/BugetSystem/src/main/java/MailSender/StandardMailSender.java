package mailsender;
public class StandardMailSender implements MailSender {
    public MailSenderStatusType status;

    @Override
    public MailSenderStatusType sendEmail(String receiverAddress,String mailContent){
        System.out.println(mailContent);
        return MailSenderStatusType.OK;
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
