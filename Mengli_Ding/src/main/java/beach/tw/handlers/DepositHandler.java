package beach.tw.handlers;

import beach.tw.requests.CustomerRequest;

/**
 * Created by mlding on 8/16/15.
 */
public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) {
        request.getCustomer().getAccount().add(request.getBill());
        request.getCustomer().setIsActivated(true);
    }
}
