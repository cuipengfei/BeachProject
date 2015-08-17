package com.second.job.tw;

import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ppyao on 8/17/15.
 */
public class MailSendTest {
    @Test
    public void bankShouldSendEmailToCustomerSuccessfully()
    {
        String message="Dear,<yaopingping>,Welcome to the Bank";
        MailSend mailSend=mock(MailSend.class);
        Customer customer=new Customer("yaopingping",new Date());

        mailSend.sendMessage(customer,message);
        verify(mailSend).sendMessage(customer,message);
    }

}