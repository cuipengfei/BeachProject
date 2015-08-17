package beach.utils;

import beach.utils.handlers.RequestHandler;
import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientException;

/**
 * Created by mlding on 8/16/15.
 */
public class Account {
    private int money = 0;

    public int getMoney() {
        return money;
    }

    public void add(int bill){
        money += bill;
    }

    public void minus(int bill){
        money -= bill;
    }

    /**
     * Created by mlding on 8/16/15.
     */
    public static class WithdrawHandler implements RequestHandler {
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
}
