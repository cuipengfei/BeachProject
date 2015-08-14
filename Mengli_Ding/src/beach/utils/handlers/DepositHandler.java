package beach.utils.handlers;

import beach.utils.requests.CustomerRequest;
import beach.utils.requests.InsufficientFundException;

/**
 * Created by mlding on 8/14/15.
 */
public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws InsufficientFundException{
        if (request.getBill() > 0)
            request.getCustomer().getAccount().addMoney(request.getBill());
    }
}
