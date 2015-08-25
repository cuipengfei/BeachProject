package handle;

import entity.Account;
import entity.Customer;
import request.CustomerRequest;

import java.util.Calendar;

public class DepositHandler implements CustomerHandler {
    @Override
    public double handle(CustomerRequest customerRequest) {
        double bonus = 5.0;
        Account account = customerRequest.getCustomer().findAccountByName(customerRequest.getAccountName());
        if (isGiveBonus(customerRequest.getCustomer())) {
            customerRequest.getCustomer().setAcceptReward(true);
            return account.addBalance(customerRequest.getAmount() + bonus);
        }
        return account.addBalance(customerRequest.getAmount());
    }

    private boolean isGiveBonus(Customer customer) {
        Calendar joinBankDay = customer.getJoinBankDay();
        joinBankDay.add(Calendar.YEAR, 2);
        return !customer.isAcceptReward() && joinBankDay.before(Calendar.getInstance());
    }

}
