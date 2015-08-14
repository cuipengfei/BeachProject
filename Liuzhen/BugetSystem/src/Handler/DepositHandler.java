package Handler;

import Request.CustomerRequest;
import Src.Customer;

/**
 * Created by zhenliu on 8/14/15.
 */
public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) {
        Customer _customer = request.getCustomer();
        double _moneyWillBeDrawn = request.getMoney();

        double currentMoneyInAccount = _customer.getAccount();

        _customer.setAccount(currentMoneyInAccount + _moneyWillBeDrawn);
    }
}
