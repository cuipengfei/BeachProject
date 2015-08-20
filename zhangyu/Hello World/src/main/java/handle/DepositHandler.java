package handle;

import request.Customer;
import request.CustomerRequest;

import java.util.Calendar;

/**
 * Created by yuzhang on 8/14/15.
 */
public class DepositHandler implements RequestHandler{
    @Override
    public void handle(CustomerRequest request) {
        int num = request.getNum();
        Customer customer = request.getCustomer();

        if(isGiveBonus(request)) {
            customer.setIsPassTwoYear(true);
            num += 5;
        }

        customer.getMyAccount().add(num);
    }

    private boolean isGiveBonus(CustomerRequest request){
        Customer customer = request.getCustomer();
        Calendar dateOfJoin = customer.getDateOfJoin();
        Calendar currentDate = Calendar.getInstance();
        dateOfJoin.add(Calendar.YEAR, 2);

        return (!customer.isPassTwoYear() && dateOfJoin.before(currentDate));
    }

}
