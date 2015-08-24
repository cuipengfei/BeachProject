package handle;

import entity.Account;
import entity.Customer;
import exception.OverdrawException;
import request.CustomerRequest;


public class WithdrawHandler implements CustomerHandler {
    @Override
    public double handle(CustomerRequest customerRequest) throws OverdrawException {
        double withdrawAmount = customerRequest.getAmount();
        Customer customer = customerRequest.getCustomer();
        Account account = customer.getAccount();
        if (!overdraft(withdrawAmount, account) || canOverdraft(withdrawAmount, customer, account)) {
            return account.minusBalance(withdrawAmount);
        }
        throw new OverdrawException("Overdraw");
    }

    private boolean canOverdraft(double withdrawAmount, Customer customer, Account account) {
        return overdraft(withdrawAmount, account)
                && customer.getAccount().isOverdraftAllowed()
                && !exceedsLimit(withdrawAmount, account);
    }

    private boolean overdraft(double amount, Account account) {
        return amount > account.getBalance();
    }

    private boolean exceedsLimit(double amount, Account account) {
        return amount - account.getBalance() > account.getOverdraftLimit();
    }

}
