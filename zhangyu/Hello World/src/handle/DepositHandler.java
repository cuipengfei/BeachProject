package handle;

import request.CustomerRequest;

/**
 * Created by yuzhang on 8/14/15.
 */
public class DepositHandler implements RequestHandler{
    @Override
    public void handle(CustomerRequest request) {
        request.getCustomer().getMyAccount().deposit(request.getNum());
    }
}
