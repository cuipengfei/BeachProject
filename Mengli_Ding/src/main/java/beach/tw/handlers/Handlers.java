package beach.tw.handlers;

import beach.tw.requests.RequestType;

import java.util.HashMap;

public class Handlers {
    private static final HashMap<RequestType, RequestHandler> handlers =
            new HashMap<RequestType, RequestHandler>(){
        {
            put(RequestType.deposit, new DepositHandler());
            put(RequestType.withdraw, new WithdrawHandler());
            put(RequestType.transfer, new TransferHandler());
        }
    };

    public static RequestHandler findHandler(RequestType type){
        return handlers.get(type);
    }
}
