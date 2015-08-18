package email;

public class MockEmailSend extends EmailSend {
    private boolean isCallEmailSend = false;

    public boolean isCallEmailSend() {
        return isCallEmailSend;
    }

    @Override
    public void sendEmail(String emailAddress, String content) {
        isCallEmailSend = true;
    }

}
