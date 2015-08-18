package handle;

import request.CustomerRequest;

/**
 * Created by yuzhang on 8/14/15.
 */
public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
        request.getCustomer().getMyAccount().sub(request.getNum());
    }
}
