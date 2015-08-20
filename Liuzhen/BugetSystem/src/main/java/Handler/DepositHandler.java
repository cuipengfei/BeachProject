package Handler;

import Request.CustomerRequest;
import Customer.*;

import java.util.Calendar;

public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) {
        Customer _customer = request.getCustomer();
        double _moneyWillBeDeposited = request.getMoney();

        double currentMoneyInAccount = _customer.getAccount();

        _customer.setAccount(currentMoneyInAccount + _moneyWillBeDeposited);

        handleTwoYearBonus(request);
    }

    private void handleTwoYearBonus(CustomerRequest request) {
        Calendar dateOfTwoYearsAfterJoinDate = request.getCustomer().getJoiningDate();
        dateOfTwoYearsAfterJoinDate.add(Calendar.YEAR, 2);
        Calendar dateOfToday = Calendar.getInstance();

        if (!request.getCustomer().hasReceivedTwoYearsBonus() && dateOfTwoYearsAfterJoinDate.before(dateOfToday)){
            request.getCustomer().setHasReceivedTwoYearsBonus(true);
            request.getCustomer().setAccount(request.getCustomer().getAccount() + 5.0);
        }
    }
}
