package handle;

import request.Customer;
import request.CustomerRequest;

/**
 * Created by yuzhang on 8/14/15.
 */
public class DepositHandler implements RequestHandler{
    @Override
    public void handle(CustomerRequest request) {
        int num = request.getNum();
        Customer customer = request.getCustomer();
        long day = (request.getCurrentDate().getTime()-customer.getDateOfJoin().getTime())/(24*60*60*1000);

        if(!customer.isPassTwoYear() && day > 365*2) {
            customer.setIsPassTwoYear(true);
            num += 5;
        }

        customer.getMyAccount().add(num);
    }


}
