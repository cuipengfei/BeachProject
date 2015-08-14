package handle;

import request.CustomerRequest;

/**
 * Created by yuzhang on 8/14/15.
 */
public interface RequestHandler {
    void handle(CustomerRequest request) throws Exception;
}
