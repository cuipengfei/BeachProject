package handle;

import entity.Customer;
import request.CustomerRequest;

import java.util.Calendar;

public class DepositHandler implements CustomerHandler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        double bonus = 5.0;
        if (isGiveBonus(customerRequest.getCustomer())) {
            customerRequest.getCustomer().setAcceptReward(true);
            return customerRequest.getCustomer().getAccount().addBalance(customerRequest.getAmount() + bonus);
        }
        return customerRequest.getCustomer().getAccount().addBalance(customerRequest.getAmount());
    }

    private boolean isGiveBonus(Customer customer) {
        Calendar joinBankDay = customer.getJoinBankDay();
        joinBankDay.add(Calendar.YEAR, 2);
        return !customer.isAcceptReward() && joinBankDay.before(Calendar.getInstance());
    }
}
