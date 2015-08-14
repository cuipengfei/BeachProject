package beach.utils.handlers;

import beach.utils.requests.RequestType;

import java.util.HashMap;

/**
 * Created by mlding on 8/14/15.
 */
public final class Handlers {
    private static final HashMap<RequestType,RequestHandler> handlers = new HashMap<RequestType,RequestHandler>() {
        {
            put(RequestType.Deposit,new DepositHandler());
            put(RequestType.WithDraw,new WithdrawHandler());
        }
    };

    public static RequestHandler findHandler(RequestType type){
        return handlers.get(type);
    }
}
