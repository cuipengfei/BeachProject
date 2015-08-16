package beach.utils.handlers;

import beach.utils.Account;
import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientException;

/**
 * Created by mlding on 8/16/15.
 */
public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws InsufficientException {
        int bill = request.getBill();
        Account account = request.getCustomer().getAccount();

        if (account.getMoney() >= bill)
            account.minus(bill);
        else
            throw new InsufficientException();
    }
}
