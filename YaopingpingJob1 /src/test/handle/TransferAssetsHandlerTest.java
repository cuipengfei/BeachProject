package handle;


import entity.Account;
import exception.OverdrawException;
import org.junit.Before;
import org.junit.Test;
import request.CustomerRequest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransferAssetsHandlerTest {
    public TransferAssetsHandler transferAssetsHandler;
    public Account fromAccount;
    public Account toAccount;

    @Before
    public void setUp() throws Exception {
        transferAssetsHandler = new TransferAssetsHandler();

        fromAccount = Account.createAccount("transferAccount");

        toAccount = Account.createAccount("receiveAccount");
    }

    @Test
    public void should_transfer_successfully_after_transfers_account_balance_greater_than_0() throws OverdrawException {

        fromAccount.addBalance(100d);

        transferAssetsHandler.handle(CustomerRequest.transferRequest(fromAccount, toAccount, 50d));

        assertThat(fromAccount.getBalance(), is(50d));

        assertThat(toAccount.getBalance(), is(50d));
    }

    @Test(expected = OverdrawException.class)
    public void should_throw_exception_after_transfers_account_balance_below_0_even_if_allowed_overdraft() throws OverdrawException {

        fromAccount.setOverdraftAllowed(true);

        transferAssetsHandler.handle(CustomerRequest.transferRequest(fromAccount, toAccount, 50d));
    }
}