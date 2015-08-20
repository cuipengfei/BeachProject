package beach.tw.handlers;

import beach.tw.entity.Account;
import beach.tw.requests.CustomerRequest;
import beach.tw.exception.InsufficientException;

/**
 * Created by mlding on 8/16/15.
 */
public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request){
        int bill = request.getBill();
        Account account = request.getCustomer().getAccount();
        if (account.getMoney() >= bill)
            account.minus(bill);
        else
            throw new InsufficientException();
    }
}
