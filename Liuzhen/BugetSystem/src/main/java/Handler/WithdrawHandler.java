package handler;

import myException.OverdrawException;
import request.CustomerRequest;
import customer.*;

public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
            Customer customer = request.getCustomer();
            double moneyWillBeDrawn = request.getMoney();
            double currentMoneyInAccount = customer.getAccount(request.getAccountName()).getBalance();
            double overdraftLimitAmount = customer.getAccount(request.getAccountName()).isOverdraftAllowed() ? 1000.0 : 0.0;

            if(moneyWillBeDrawn <= (currentMoneyInAccount + overdraftLimitAmount)) {
                customer.getAccount(request.getAccountName()).setBalance(currentMoneyInAccount - moneyWillBeDrawn);
            }
            else throw new OverdrawException();
    }
}
