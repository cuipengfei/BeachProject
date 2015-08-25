package handler;

import request.RequestType;
import java.util.HashMap;

public class Handlers {
    private static final HashMap<RequestType,RequestHandler> requestHandlerHashMap = new HashMap<RequestType,RequestHandler> (){
        {
            put(RequestType.deposit,new DepositHandler());
            put(RequestType.withDraw,new WithdrawHandler());
            put(RequestType.transfer,new TransferHandler());
        }
    };

    public static RequestHandler findHandler(RequestType requestType) {
        return requestHandlerHashMap.get(requestType);
    }
}
