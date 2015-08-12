package com.tw.beach.entity;

public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws InsufficientFundException {
        request.getCustomer().account().add(request.getAmount());
    }
}
