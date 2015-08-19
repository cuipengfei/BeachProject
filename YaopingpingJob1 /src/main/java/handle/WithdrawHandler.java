package handle;

import entity.Account;
import exception.OverdraftException;
import request.CustomerRequest;


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