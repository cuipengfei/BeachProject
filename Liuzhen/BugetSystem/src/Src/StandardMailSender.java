package Src;

public class StandardMailSender implements MailSender {
    @Override
    public void sendEmail(String receiverAddress,String mailContent){
        System.out.println(mailContent);
    }
}
