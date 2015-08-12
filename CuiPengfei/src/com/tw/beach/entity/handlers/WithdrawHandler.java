package com.tw.beach.entity.handlers;

import com.tw.beach.entity.Account;
import com.tw.beach.entity.requests.CustomerRequest;
import com.tw.beach.entity.requests.InsufficientFundException;

public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws InsufficientFundException {
        Integer amount = request.getAmount();
        Account account = request.getCustomer().account();

        if (amount <= account.balance()) {
            account.minus(amount);
        } else {
            throw new InsufficientFundException();
        }
    }
}
