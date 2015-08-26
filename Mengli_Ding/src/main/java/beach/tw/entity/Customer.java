package beach.tw.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static beach.tw.entity.Account.invalidAccount;

/**
 * Created by mlding on 8/15/15.
 */
public class Customer {
    private final String name;
    private final Date birthdDate;
    private Account account;
    private boolean isPremiumCustomer;
    private boolean isGetBonus;
    private Calendar joiningDate;
    private static List<Account> accountList = new ArrayList<>();
    private static final Customer invalidCustomer = new Customer(null, null);

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setJoiningDate(Calendar joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Calendar getJoiningDate() {
        return joiningDate;
    }

    public void setIsGetBonus(boolean isGetBonus) {
        this.isGetBonus = isGetBonus;
    }

    public boolean isGetBonus() {
        return isGetBonus;
    }

    public boolean isPremiumCustomer() {
        return isPremiumCustomer;
    }

    public void setIsPremiumCustomer(boolean isPremiumCustomer) {
        this.isPremiumCustomer = isPremiumCustomer;
    }

    public Account getAccount(String name) {
        for (Account account : accountList) {
            if (account.getAccountName().equals(name))
                return account;
        }
        return invalidAccount();
    }

    public String getName() {
        return name;
    }

    private Customer(String name, Date date) {
        this.name = name;
        this.birthdDate = date;
    }

    public static Customer invalidCustomer() {
        return invalidCustomer;
    }

    private static boolean isNameValid(String name) {
        if (name == null) return false;
        return name.matches("^[a-z0-9]+$");
    }

    public static Customer createCustomer(String name, Date date) {
        if (isNameValid(name)) {
            return new Customer(name, date);
        } else {
            return invalidCustomer();
        }
    }

    public boolean addAccount(Account account) {
        assertInvalid(account);

        boolean isShouleAdd = isNotSameAccount(account);
        if (isShouleAdd) {
            accountList.add(account);
        }
        return isShouleAdd;
    }

    private void assertInvalid(Account account) {
        if (account == null || account == invalidAccount()) {
            throw new IllegalArgumentException("account is null");
        }
    }

    private boolean isNotSameAccount(Account account) {
        for (Account account1 : accountList) {
            if (account1.getAccountName().equals(account.getAccountName()))
                return false;
        }
        return true;
    }

    public static void printAccountInfo(Customer customer) {
        StringBuffer info = new StringBuffer("Statement for " + customer.getName());
        for (Account account2 : accountList) {
            info.append("\n");
            info.append(account2.getAccountName() + " " +account2.getMoney() );
        }
        System.out.println(info);
    }
}
