package handle;


import exception.OverdrawException;
import request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public interface CustomerHandler {
    double handle(CustomerRequest customerRequest) throws OverdrawException;
}
