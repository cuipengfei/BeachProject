package handle;

import entity.Account;
import entity.Customer;
import exception.OverdraftException;
import request.CustomerRequest;


/**
 * Created by ppyao on 8/13/15.
 */
public class WithdrawHandler implements CustomerHandler {
    @Override
    public double handlers(CustomerRequest customerRequest) throws OverdraftException {
        double money = customerRequest.getMoney();
        Account account = customerRequest.getCustomer().getAccount();
        if (money <= account.getBalance()) {
            return account.minusBalance(money);
        }
        else if(overdraftLimit1000(customerRequest.getCustomer()))
        {
            return account.minusBalance(money);
        }
        throw new OverdraftException();
    }

    public boolean overdraftLimit1000(Customer customer) throws OverdraftException{
        double overdraftAmount = 1000;
        if (customer.isOverdraftAllowed()) {
            customer.getAccount().addBalance(overdraftAmount);
            return  true;
        }
        else
        {
            throw new OverdraftException();
        }
    }
}