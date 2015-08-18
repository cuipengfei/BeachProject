package com.second.job.tw.handle;

import com.second.job.tw.BankManager;
import com.second.job.tw.MailSender;
import com.second.job.tw.OverdraftException;
import com.second.job.tw.request.CustomerRequest;


/**
 * Created by ppyao on 8/13/15.
 */
public class DespoitHandler implements CustomerHandler {
    BankManager bankManager;
    MailSender sender;

    @Override
    public double handlers(CustomerRequest customerRequest) throws OverdraftException {
        if (customerRequest.getMoney() > 0) {
            customerRequest.getCustomer().getAccount().addBalance(customerRequest.getMoney());
            return customerRequest.getCustomer().getAccount().getBalance();
        }
        return customerRequest.getCustomer().getAccount().getBalance();

    }
}
