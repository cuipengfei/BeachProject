package com.tw.beach.entity;

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
