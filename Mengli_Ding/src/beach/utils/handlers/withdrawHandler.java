package beach.utils.handlers;

import beach.utils.requests.CustomerRequest;

/**
 * Created by mlding on 8/14/15.
 */
public class withdrawHandler implements RequestHandler{
    @Override
    public void handle(CustomerRequest request) {
        request.getCustomer().getAccount().minusMoney(request.getBill());
    }
}
