package beach.tw.handlers;

import beach.tw.entity.Account;
import beach.tw.entity.Customer;
import beach.tw.requests.CustomerRequest;
import beach.tw.exception.InsufficientException;

/**
 * Created by mlding on 8/16/15.
 */
public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws InsufficientException{
        int bill = request.getBill();
        Customer customer = request.getCustomer();
        Account account = customer.getAccount(request.getAccountName());
        int currentmoney = account.getMoney();
        int overdraftmoney = account.isOverdraft() ? account.getLimit() : 0;

        if (currentmoney + overdraftmoney < bill) {
            throw new InsufficientException();
        } else {
            account.minus(bill);
        }
    }
}
