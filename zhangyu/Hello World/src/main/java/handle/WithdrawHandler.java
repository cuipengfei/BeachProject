package handle;

import exception.OverDrawException;
import request.Account;
import request.CustomerRequest;

/**
 * Created by yuzhang on 8/14/15.
 */
public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
        int num = request.getNum();
        Account account = request.getCustomer().getMyAccount();
        if (num > account.getBalance()) {
            throw new OverDrawException("overdraw");
        }
        account.sub(num);

    }
}
