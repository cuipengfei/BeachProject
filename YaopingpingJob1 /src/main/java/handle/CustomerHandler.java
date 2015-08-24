package handle;


import exception.OverdrawException;
import request.CustomerRequest;

public interface CustomerHandler {
    double handle(CustomerRequest customerRequest) throws OverdrawException;
}
