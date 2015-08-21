package Handler;

import MyException.OverdrawException;
import Request.CustomerRequest;
import Customer.*;

public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
            Customer _customer = request.getCustomer();
            double _moneyWillBeDrawn = request.getMoney();
            double currentMoneyInAccount = _customer.getAccount();
            double overdraftLimitMount = _customer.isOverdraftAllowed()? 1000.0 : 0.0;

            if(_moneyWillBeDrawn <= (currentMoneyInAccount + overdraftLimitMount)) {
                _customer.setAccount(currentMoneyInAccount - _moneyWillBeDrawn);
            }
            else throw new OverdrawException();
    }
}
