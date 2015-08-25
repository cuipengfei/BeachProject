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
    private List<Account> accountList = new ArrayList<>();
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
        for (Account accountTemp : accountList) {
            if (accountTemp.getAccountName().equals(name))
                return accountTemp;
        }
        return invalidAccount();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public Customer(String name, Date date) {
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
        boolean isShouleAdd = isNotSameAccount(account);
        if (isShouleAdd) {
            accountList.add(account);
        }
        return isShouleAdd;
    }

    private boolean isNotSameAccount(Account account) {
        for (Account accountTemp : accountList) {
            if (accountTemp.getAccountName().equals(account.getAccountName()))
                return false;
        }
        return true;
    }

}
