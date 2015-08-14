package Handler;

import Request.RequestType;
import java.util.HashMap;

public class Handlers {
    private static final HashMap<RequestType,RequestHandler> requestHandlerHashMap = new HashMap<RequestType,RequestHandler> (){
        {
            put(RequestType.deposit,new DepositHandler());
            put(RequestType.withDraw,new WithdrawHandler());
        }
    };

    public static RequestHandler findHandler(RequestType requestType) {
        return requestHandlerHashMap.get(requestType);
    }
}
