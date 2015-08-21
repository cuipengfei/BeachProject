package handle;

import exception.OverDrawException;
import request.Account;
import request.CustomerRequest;

public class WithdrawHandler implements RequestHandler {

    private int limit;

    public WithdrawHandler(int overdraftLimit) {
        this.limit = overdraftLimit;
    }

    @Override
    public void handle(CustomerRequest request) throws Exception {
        int num = request.getNum();
        Account account = request.getCustomer().getMyAccount();
        int overDrawNum = num - account.getBalance();
        if(overDrawNum > limit){
            throw new OverDrawException("overdraw");
        }else{
            account.sub(num);
        }
    }
}
