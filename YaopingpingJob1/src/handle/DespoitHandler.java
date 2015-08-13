package handle;

import com.second.job.tw.OverdraftException;
import com.second.job.tw.request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public class DespoitHandler implements CustomerHandler {
    @Override
    public void handlers(CustomerRequest customerRequest) throws OverdraftException {
        customerRequest.getCustomer().getAccount().setBalance(customerRequest.getMoney());
    }
}
