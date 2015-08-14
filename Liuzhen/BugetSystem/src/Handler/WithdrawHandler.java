package Handler;

import MyException.OverdrawException;
import Request.CustomerRequest;
import Src.Customer;

public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
            Customer _customer = request.getCustomer();
            double _moneyWillBeDrawn = request.getMoney();
            double currentMoneyInAccount = _customer.getAccount();

            if(_moneyWillBeDrawn < currentMoneyInAccount) {
                _customer.setAccount(currentMoneyInAccount - _moneyWillBeDrawn);
            }
            else throw new OverdrawException();
    }
}
