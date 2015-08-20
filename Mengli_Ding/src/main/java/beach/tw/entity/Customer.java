package beach.tw.entity;

import java.util.Date;

/**
 * Created by mlding on 8/15/15.
 */
public class Customer {
    private final String name;
    private final Date birthdDate;
    private final Account account = new Account();
    private boolean isPremiumCustomer = false;
    private boolean isActivated = false;
    private Date joiningDate;

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean isPremiumCustomer() {
        return isPremiumCustomer;
    }

    public void setIsPremiumCustomer(boolean isPremiumCustomer) {
        this.isPremiumCustomer = isPremiumCustomer;
    }

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    private static final Customer invalidCustomer = new Customer(null, null);

    public Customer(String name, Date date){
        this.name = name;
        this.birthdDate = date;
    }

    public static Customer invalidCustomer(){
        return invalidCustomer;
    }

    private static boolean isNameValid(String name){
        if (name == null) return false;
        return name.matches("^[a-z0-9]+$");
    }

    public static Customer createCustomer(String name, Date date){
        if (isNameValid(name))
            return new Customer(name, date);
        else
            return invalidCustomer();
    }

}
