package Src;

public class MailSender {
    public void sendEmail(String senderAddress, String receiverAddress, String mailTitle,String mailContent){
        System.out.println(receiverAddress+" receive an email from "+senderAddress);
        System.out.println("title:"+mailTitle);
        System.out.println("content:"+mailContent);
    }
}
