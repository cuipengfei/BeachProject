package request;

/**
 * Created by yuzhang on 8/17/15.
 */
public class MockEmailSend extends EmailSend {
    private boolean isCallEmailSend = false;

    public boolean isCallEmailSend() {
        return isCallEmailSend;
    }

    @Override
    public void sendEmail(Customer receiver) {
        isCallEmailSend = true;
    }
}
