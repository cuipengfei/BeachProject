package beach.tw.handlers;

import beach.tw.entity.Account;
import beach.tw.entity.Customer;
import beach.tw.requests.CustomerRequest;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mlding on 8/16/15.
 */
public class DepositHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) {
        Customer customer = request.getCustomer();
        Account account = customer.getAccount(request.getAccountName());
        if (isCustomerBeenWithBankOverTwoYears(customer) && !customer.isGetBonus()){
            account.add(request.getBill() + 5);
            customer.setIsGetBonus(true);
        }
        else{
            account.add(request.getBill());
        }
    }

    private boolean isCustomerBeenWithBankOverTwoYears(Customer customer) {
        Calendar joiningDate = customer.getJoiningDate();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        joiningDate.add(Calendar.YEAR, 2);
        return joiningDate.before(calendar2);
    }

}
