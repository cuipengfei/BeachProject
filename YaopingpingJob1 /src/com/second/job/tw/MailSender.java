package com.second.job.tw;

/**
 * Created by ppyao on 8/17/15.
 */
public class MailSender {
    public void sendEmail(Customer customer,String message)
    {
        customer.setMessage(message);
    }

}
