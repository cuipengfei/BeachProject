package com.second.job.tw.handle;

import com.second.job.tw.Account;
import com.second.job.tw.OverdraftException;
import com.second.job.tw.request.CustomerRequest;


/**
 * Created by ppyao on 8/13/15.
 */
public class WithdrawHandler implements CustomerHandler {
    @Override
    public double handlers(CustomerRequest customerRequest) throws OverdraftException {
        double money = customerRequest.getMoney();
        Account account = customerRequest.getCustomer().getAccount();
        if (money <= account.getBalance()) {
            return account.minusBalance(money);
        }
        throw new OverdraftException();

    }
}