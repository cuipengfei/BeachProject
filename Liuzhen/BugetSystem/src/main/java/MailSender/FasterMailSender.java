package MailSender;

public class FasterMailSender implements MailSender{
    @Override
    public void sendEmail(String receiverAddress,String mailContent){
        System.out.println("It's faster mail sender!"+mailContent);
    }
}
