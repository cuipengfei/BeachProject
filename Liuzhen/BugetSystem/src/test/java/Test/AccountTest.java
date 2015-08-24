package test;

import account.Account;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by zhenliu on 8/25/15.
 */
public class AccountTest {
    @Test
    public void should_accounts_have_a_default_name_when_add_a_account() throws Exception {
        Account account = new Account();

        assertTrue(account.getName() != null);
    }
}
