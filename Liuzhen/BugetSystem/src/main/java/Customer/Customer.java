package Customer;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenliu on 8/11/15.
 */
public class Customer {
    private static final Customer invalidCustomer = new Customer(null,null);

    private final String nickName;
    private final Calendar dateOfBirth;
    private double account = 0.0;
    private String message ="";
    private boolean isPremiumCustomer = false;
    private Calendar joiningDate;
    private double twoYearsBonus = 0.0;

    private Customer(String _nickName, Calendar _dateOfBirth)  {
        nickName = _nickName;
        dateOfBirth = _dateOfBirth;
    }
    public static Customer getInvalidCustomer() {return invalidCustomer;}

    public static Customer createCustomer(String _nickName, Calendar _dateOfBirth) {
        if (isValidNickName(_nickName))
            return new Customer(_nickName, _dateOfBirth);
        else
            return invalidCustomer;
    }

    private static boolean isValidNickName(String _nickName) {
        String reg = "^[a-z0-9]+$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(_nickName);

        return matcher.matches();
    }

    public String getNickName(){
        return nickName;
    }

    public void setAccount(double _money) {
        this.account = _money;
    }

    public double getAccount() {
        return account;
    }

    public void setIsPremiumCustomer(boolean isPremiumCustomer) {
        this.isPremiumCustomer = isPremiumCustomer;
    }

    public boolean isPremiumCustomer() {
        return isPremiumCustomer;
    }

    public void setJoiningDate(Calendar joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Calendar getJoiningDate() {
        return joiningDate;
    }

    public double getTwoYearsBonus() {
        return twoYearsBonus;
    }

    public void setTwoYearsBonus(double twoYearsBonus) {
        this.twoYearsBonus = twoYearsBonus;
    }
}
