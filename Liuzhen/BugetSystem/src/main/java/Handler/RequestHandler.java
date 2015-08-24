package handler;

import request.CustomerRequest;

public interface RequestHandler {
     void handle(CustomerRequest request) throws Exception;
}
