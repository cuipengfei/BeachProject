package request;

/**
 * Created by yuzhang on 8/17/15.
 */
public class EmailSend {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void sendEmail(Customer receiver){
        receiver.getMyMailBox().setMessage(content);
    }

    public void sendEmail(Manager manager){
        manager.getMailBox().setMessage(content);
    }

}
