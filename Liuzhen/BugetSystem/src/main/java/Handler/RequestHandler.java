package Handler;

import Request.CustomerRequest;

public interface RequestHandler {
     void handle(CustomerRequest request) throws Exception;
}
