package handler;

import request.CustomerRequest;
import customer.*;

import java.util.Calendar;

public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
        Customer customer = request.getCustomer();
        double _moneyWillBeDeposited = request.getMoney();

        double currentMoneyInAccount = customer.getAccount(request.getAccountName()).getBalance();

        customer.getAccount(request.getAccountName()).setBalance(currentMoneyInAccount + _moneyWillBeDeposited);

        handleTwoYearBonus(request);
    }

    private void handleTwoYearBonus(CustomerRequest request) throws Exception {
        Calendar dateOfTwoYearsAfterJoinDate = request.getCustomer().getJoiningDate();
        dateOfTwoYearsAfterJoinDate.add(Calendar.YEAR, 2);
        Calendar dateOfToday = Calendar.getInstance();

        if (!request.getCustomer().hasReceivedTwoYearsBonus() && dateOfTwoYearsAfterJoinDate.before(dateOfToday)){
            request.getCustomer().setHasReceivedTwoYearsBonus(true);
            request.getCustomer().getAccount(request.getAccountName()).setBalance(request.getCustomer().getAccount(request.getAccountName()).getBalance() + 5.0);
        }
    }
}
