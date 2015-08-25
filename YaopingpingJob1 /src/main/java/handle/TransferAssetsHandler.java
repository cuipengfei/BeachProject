package handle;


import entity.Account;
import exception.OverdrawException;
import request.CustomerRequest;

public class TransferAssetsHandler implements CustomerHandler {
    public void handle(CustomerRequest customerRequest) throws OverdrawException {
        Account fromAccount = customerRequest.getFromAccount();
        Account toAccount = customerRequest.getToAccount();
        double amount = customerRequest.getAmount();
        transferOperation(fromAccount, toAccount, amount);
    }

    private void transferOperation(Account fromAccount, Account toAccount, double amount) throws OverdrawException {
        if (fromAccount.getBalance() - amount >= 0) {
            fromAccount.minusBalance(amount);
            toAccount.addBalance(amount);
        } else {
            throw new OverdrawException("the account's balance is insufficient");
        }
    }
}
