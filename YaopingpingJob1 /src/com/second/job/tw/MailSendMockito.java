package com.second.job.tw;

/**
 * Created by ppyao on 8/17/15.
 */
public class MailSendMockito extends MailSender {
    boolean isSendMailCalled = false;

    public boolean isSendMailCalled() {
        return isSendMailCalled;
    }

    public void sendEmail(Customer customer, String message) {
        customer.setMessage(message);
        isSendMailCalled = true;
    }

    public void sendEmail(BankManager bankManager, String message) {

    }
}
