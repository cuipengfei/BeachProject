package request;

/**
 * Created by yuzhang on 8/17/15.
 */
public class EmailSend {
    private String content;

    public void sendEmail(Customer receiver){
        content = "Dear " + receiver.getNickname() + " , Welcome to bank";
        receiver.getMyMailBox().setMessage(content);
    }

}
