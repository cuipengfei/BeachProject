package com.tw.beach.entity.handlers;

import com.tw.beach.entity.requests.CustomerRequest;
import com.tw.beach.entity.requests.InsufficientFundException;

public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws InsufficientFundException {
        request.getCustomer().account().add(request.getAmount());
    }
}
