package Handler;


import Request.CustomerRequest;

/**
 * Created by zhenliu on 8/14/15.
 */
public interface RequestHandler {
     void handle(CustomerRequest request) throws Exception;

}
