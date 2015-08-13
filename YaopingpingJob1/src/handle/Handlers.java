package handle;

import com.second.job.tw.request.RequestType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ppyao on 8/13/15.
 */
public final class Handlers {
    public final static Map<RequestType ,CustomerHandler>  handlerMap=new HashMap<RequestType,CustomerHandler>(){{
        put(RequestType.depositMoney, new DespoitHandler());
        put(RequestType.withdrawMoney, new WithdrawHandler());
    }};
    public static CustomerHandler findHandle(RequestType type){
       return  handlerMap.get(type);
    }
}
