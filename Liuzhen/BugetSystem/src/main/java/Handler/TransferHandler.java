package handler;

import account.Account;
import myException.OverTransferException;
import myException.TransferMoneyIsNotPositiveException;
import request.CustomerRequest;
import request.RequestType;

/**
 * Created by zhenliu on 8/25/15.
 */
public class TransferHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
        Account accountTransferFrom = request.getAccountTransferFrom();
        Account accountTransferTo = request.getAccountTransferTo();
        RequestType requestType = request.getRequestType();
        double requestMoney = request.getMoney();

        if (requestMoney <= 0.0){
            throw new TransferMoneyIsNotPositiveException();
        }else {
            if (accountTransferFrom.getBalance() <= requestMoney){
                throw new OverTransferException();
            }else {
                accountTransferFrom.minusMoney(requestMoney);
                accountTransferTo.addMoney(requestMoney);
            }
        }
    }
}
