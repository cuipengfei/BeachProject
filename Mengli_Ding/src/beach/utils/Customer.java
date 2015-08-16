package beach.utils;

import java.util.Date;

/**
 * Created by mlding on 8/15/15.
 */
public class Customer {
    private final String name;
    private final Date date;
    private final Account account = new Account();
    private Email email = new Email();

    public Email getEmail() {
        return email;
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
        this.date = date;
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
