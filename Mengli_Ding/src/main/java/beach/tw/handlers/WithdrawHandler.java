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
    public void handle(CustomerRequest request){
        int bill = request.getBill();
        Customer customer = request.getCustomer();
        Account account = customer.getAccount();
        int money = account.getMoney();

        if (money < bill){
            if (customer.isOverdraft() && (customer.getLimit() + money >= bill )){
                account.minus(bill);
            }
            else {
                throw new InsufficientException();
            }
        }
        else{
            account.minus(bill);
        }
    }
}
