package handle;


import exception.OverdraftException;
import request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public interface CustomerHandler {
    double handlers(CustomerRequest customerRequest) throws OverdraftException;
}
