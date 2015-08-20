package beach.tw.handlers;

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

        if (IsCustomerBeenWithBankOverTwoYears(request.getCustomer()))
            request.getCustomer().getAccount().add(request.getBill() + 5);
        else
            request.getCustomer().getAccount().add(request.getBill());
    }

    private boolean IsCustomerBeenWithBankOverTwoYears(Customer customer){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(customer.getJoiningDate());
        calendar2.setTime(new Date());
        calendar1.add(Calendar.YEAR, 2);
        return calendar1.before(calendar2);
    }

}
