package request;

/**
 * Created by yuzhang on 8/17/15.
 */
public class MockEmailSend extends EmailSend {
    private boolean isCallEmailSendToCustomer = false;
    private boolean isCallEmailSendToManager = false;

    public boolean isCallEmailSendToCustomer() {return isCallEmailSendToCustomer;}
    public boolean isCallEmailSendToManager() {return isCallEmailSendToManager;}

    @Override
    public void sendEmail(Customer receiver) {
        isCallEmailSendToCustomer = true;
    }

    @Override
    public void sendEmail(Manager manager) {isCallEmailSendToManager = true;}
}
