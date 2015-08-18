package com.thoughtworks.handlers;

import com.thoughtworks.requests.RequestType;

import java.util.HashMap;
import java.util.Map;

public class Handlers {

    private static Map<RequestType, Handler> handlers = new HashMap<RequestType, Handler>() {
        {
            put(RequestType.Deposit, new DepositHandler());
            put(RequestType.Withdraw, new WithdrawHandler());
        }
    };

    public static Handler findHandler(RequestType type) {
        return handlers.get(type);
    }
}
