package main.java.com.thoughtworks;

import java.util.HashMap;
import java.util.Map;

public class Handlers {

    static Map<RequestType, Handler> handlers = new HashMap<RequestType, Handler>() {
        {
            put(RequestType.Deposit, new Deposit());
            put(RequestType.Withdraw, new Withdraw());
        }
    };
       public static Handler findHandler(RequestType type) {
        return handlers.get(type);
    }
}
