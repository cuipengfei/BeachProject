package handle;

import java.util.HashMap;

/**
 * Created by yuzhang on 8/14/15.
 */
public class Handlers {
    private static HashMap<Type, RequestHandler> handlers = new HashMap<Type, RequestHandler>() {{
        put(Type.deposit, new DepositHandler());
        put(Type.withdraw, new WithdrawHandler());
    }};

    public static RequestHandler findHandler(Type requestType) {
        return handlers.get(requestType);
    }
}
