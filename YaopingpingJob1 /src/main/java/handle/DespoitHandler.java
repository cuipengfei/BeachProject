package handle;

import exception.OverdraftException;
import request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public class DespoitHandler implements CustomerHandler {
    @Override
    public double handlers(CustomerRequest customerRequest) throws OverdraftException {
        if (customerRequest.getMoney() > 0) {
            customerRequest.getCustomer().getAccount().addBalance(customerRequest.getMoney());
            return customerRequest.getCustomer().getAccount().getBalance();
        }
        return customerRequest.getCustomer().getAccount().getBalance();

    }
}
