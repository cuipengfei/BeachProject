package beach.utils.handlers;

import beach.utils.Account;
import beach.utils.requests.RequestType;

import java.util.HashMap;

/**
 * Created by mlding on 8/16/15.
 */
public class Handlers {
    private static final HashMap<RequestType, RequestHandler> handlers =
            new HashMap<RequestType, RequestHandler>(){
        {
            put(RequestType.deposit, new DepositHandler());
            put(RequestType.withdraw, new Account.WithdrawHandler());
        }
    };

    public static RequestHandler findHandler(RequestType type){
        return handlers.get(type);
    }
}
