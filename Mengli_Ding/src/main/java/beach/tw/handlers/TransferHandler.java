package beach.tw.handlers;

import beach.tw.entity.Account;
import beach.tw.exception.InsufficientException;
import beach.tw.requests.CustomerRequest;

/**
 * Created by mlding on 8/16/15.
 */
public class TransferHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) {
        int bill = request.getBill();
        Account accountOut = request.getAccountOut();
        Account accountIn = request.getAccountIn();
        int currentmoney = accountOut.getMoney();

        if (currentmoney < bill) {
            throw new InsufficientException();
        } else {
            accountOut.minus(bill);
            accountIn.add(bill);
        }
    }
}
