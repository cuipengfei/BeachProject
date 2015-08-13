package handle;

import com.second.job.tw.Account;
import com.second.job.tw.OverdraftException;
import com.second.job.tw.request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public class WithdrawHandler implements CustomerHandler {
    @Override
    public void handlers(CustomerRequest customerRequest) throws OverdraftException {
        double amount = customerRequest.getMoney();
        Account account = customerRequest.getCustomer().getAccount();

        if (amount <= account.getBalance()) {
            account.minusBalance(amount);
        } else {
            throw new OverdraftException();
        }
    }
}
