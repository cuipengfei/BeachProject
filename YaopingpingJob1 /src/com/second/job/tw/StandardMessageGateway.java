package com.second.job.tw;

/**
 * Created by ppyao on 8/18/15.
 */
public class StandardMessageGateway implements  MessageGateway {
    @Override
    public void sendEmail(String eamilAdress, String message) {
        System.out.println("I am standard message gateway");
    }
}
