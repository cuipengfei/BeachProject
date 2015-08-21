package handle;

import request.Type;

import java.util.HashMap;

public class Handlers {
    private static HashMap<Type, RequestHandler> handlers = new HashMap<Type, RequestHandler>() {{
        put(Type.deposit, new DepositHandler());
        put(Type.withdraw, new WithdrawHandler(0));
        put(Type.overdraft, new WithdrawHandler(1000));
    }};

    public static RequestHandler findHandler(Type requestType) {
        return handlers.get(requestType);
    }
}
