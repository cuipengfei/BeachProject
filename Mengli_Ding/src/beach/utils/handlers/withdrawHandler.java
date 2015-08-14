package beach.utils.handlers;

import beach.utils.Account;
import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientFundException;

/**
 * Created by mlding on 8/14/15.
 */
public class WithdrawHandler implements RequestHandler{
    @Override
    public void handle(CustomerRequest request) throws InsufficientFundException{
        int money = request.getBill();
        Account account = request.getCustomer().getAccount();

        if (account.getMoney() >= money)
            account.minusMoney(money);
        else throw new InsufficientFundException();
    }
}
