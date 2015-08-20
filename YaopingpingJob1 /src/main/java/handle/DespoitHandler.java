package handle;

import entity.Customer;
import request.CustomerRequest;

import java.util.Calendar;

/**
 * Created by ppyao on 8/13/15.
 */
public class DespoitHandler implements CustomerHandler {
    @Override
    public double handlers(CustomerRequest customerRequest) {
        double bouse=5.0;
        if (isGiveBouse(customerRequest.getCustomer())) {
                  customerRequest.getCustomer().setAcceptReward(true);
            return customerRequest.getCustomer().getAccount().addBalance(customerRequest.getMoney() + bouse);

        }
        return customerRequest.getCustomer().getAccount().addBalance(customerRequest.getMoney());
    }

    private boolean isGiveBouse(Customer customer) {
        Calendar joinBankDay = customer.getJoinBankDay();
        joinBankDay.add(Calendar.YEAR,2);
        return !customer.isAcceptReward() && joinBankDay.before(Calendar.getInstance());

    }
}