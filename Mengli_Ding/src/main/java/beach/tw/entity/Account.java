package beach.tw.entity;

/**
 * Created by mlding on 8/16/15.
 */
public class Account {
    private int money;
    private int limit;
    private String accountName;
    private boolean isOverdraft;
    private static final Account invalidAccount = new Account(null);

    public static Account invalidAccount() {
        return invalidAccount;
    }

    public Account(String accountName) {
        this.accountName = accountName;
    }

    public boolean isOverdraft() {
        return isOverdraft;
    }

    public void setIsOverdraft(boolean isOverdraft) {
        this.isOverdraft = isOverdraft;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getMoney() {
        return money;
    }

    public void add(int bill) {
        money += bill;
    }

    public void minus(int bill) {
        money -= bill;
    }

}
