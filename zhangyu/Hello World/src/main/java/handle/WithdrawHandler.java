package handle;

import exception.OverDrawException;
import exception.OverLimitException;
import request.Account;
import request.CustomerRequest;

/**
 * Created by yuzhang on 8/14/15.
 */
public class WithdrawHandler implements RequestHandler {
    @Override
    public void handle(CustomerRequest request) throws Exception {
        int num = request.getNum();
        boolean isOverDraftAllowed = request.getCustomer().isOverdraftAllowed();
        Account account = request.getCustomer().getMyAccount();
        int overDrawNum = num - account.getBalance();

        if(!isOverDraftAllowed){
            if(overDrawNum > 0){
                throw new OverDrawException("overdraw");
            }else{
                account.sub(num);
            }
        } else {
            if (overDrawNum > 1000){
                throw new OverLimitException("overdraw limit is 1000");
            }else {
                account.sub(num);
            }
        }
    }
}
