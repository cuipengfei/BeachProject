package request;

/**
 * Created by yuzhang on 8/17/15.
 */
public class Email {
    private String sender;
    private String content;

    public void sendEmail(Customer receiver){
        content = "Dear " + receiver.getNickname() + " , Welcome to bank";
        receiver.getMyMailBox().setMessage(content);
    }

}
