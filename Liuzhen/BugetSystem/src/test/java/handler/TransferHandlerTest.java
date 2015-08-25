package handler;

import account.Account;
import myException.OverTransferException;
import myException.TransferMoneyIsNotPositiveException;
import org.junit.Before;
import org.junit.Test;
import request.CustomerRequest;
import request.RequestType;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by zhenliu on 8/25/15.
 */
public class TransferHandlerTest {
    public Account accountTransferFrom = new Account();
    public Account accountTransferTo = new Account();

    @Before
    public void setUp() throws Exception {
        accountTransferFrom.addMoney(3000.0);
    }

    @Test
    public void should_transfer_successfully_when_an_account_with_money_more_than_money_will_be_transferred() throws Exception {
        CustomerRequest request = CustomerRequest.transfer(accountTransferFrom, accountTransferTo, RequestType.transfer, 1000.0);
        Handlers.findHandler(request.getRequestType()).handle(request);

        assertThat(accountTransferFrom.getBalance(), is(2000.0));
        assertThat(accountTransferTo.getBalance(), is(1000.0));
    }

    @Test(expected = TransferMoneyIsNotPositiveException.class)
    public void should_throw_exception_when_money_will_be_transferred_is_not_positive() throws Exception {
        CustomerRequest request = CustomerRequest.transfer(accountTransferFrom, accountTransferTo, RequestType.transfer, -1000.0);
        Handlers.findHandler(request.getRequestType()).handle(request);
    }

    @Test(expected = OverTransferException.class)
    public void should_throw_exception_when_an_account_with_money_less_than_money_will_be_transferred() throws Exception {
        CustomerRequest request = CustomerRequest.transfer(accountTransferFrom, accountTransferTo, RequestType.transfer, 4000.0);
        Handlers.findHandler(request.getRequestType()).handle(request);
    }

    @Test(expected = OverTransferException.class)
    public void should_throw_exception_when_an_account_with_money_less_than_money_will_be_transferred_even_the_account_is_over_draft_allowed() throws Exception {
        accountTransferFrom.setOverdraftAllowed(true);

        CustomerRequest request = CustomerRequest.transfer(accountTransferFrom, accountTransferTo, RequestType.transfer, 4000.0);
        Handlers.findHandler(request.getRequestType()).handle(request);
    }

}