package handle;

import entity.BankManager;
import request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public class DespoitHandler implements CustomerHandler {
    @Override
    public double handlers(CustomerRequest customerRequest) {
        if (!customerRequest.getCustomer().isAcceptReward() && BankManager.calInterval(customerRequest.getCustomer())) {
            customerRequest.getCustomer().setAcceptReward(true);
            return customerRequest.getCustomer().getAccount().addBalance(customerRequest.getMoney() + 5.0);

        }
        return customerRequest.getCustomer().getAccount().addBalance(customerRequest.getMoney());
    }
}