package handle;


import exception.OverdrawException;
import request.CustomerRequest;

public interface CustomerHandler {
    void handle(CustomerRequest customerRequest) throws OverdrawException;
}
