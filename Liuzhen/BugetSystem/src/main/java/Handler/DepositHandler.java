package Handler;

import Request.CustomerRequest;
import Customer.*;

public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) {
        Customer _customer = request.getCustomer();
        double _moneyWillBeDrawn = request.getMoney();

        double currentMoneyInAccount = _customer.getAccount();

        _customer.setAccount(currentMoneyInAccount + _moneyWillBeDrawn);
    }
}
