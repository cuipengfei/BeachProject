package beach.utils.handlers;

import beach.utils.requests.CustomerRequest;

/**
 * Created by mlding on 8/16/15.
 */
public interface RequestHandler {
    void handle(CustomerRequest request);
}
