package customer;

import account.Account;
import myException.AccountNameNotUniqueException;
import myException.AccountNotExistException;
import myException.OverTransferException;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenliu on 8/11/15.
 */
public class Customer {
    private static final Customer invalidCustomer = new Customer(null,null);

    private final String nickName;
    private final Calendar dateOfBirth;
    private LinkedList<Account> accountsList = new LinkedList<Account>(){
        {
            add(new Account("current"));
        }
    };
    private String message ="";
    private boolean isPremiumCustomer = false;
    private Calendar joiningDate;
    private boolean hasReceivedTwoYearsBonus;

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

    public boolean hasReceivedTwoYearsBonus() {
        return hasReceivedTwoYearsBonus;
    }

    public void setHasReceivedTwoYearsBonus(boolean hasReceivedTwoYearsBonus) {
        this.hasReceivedTwoYearsBonus = hasReceivedTwoYearsBonus;
    }

    public Account getAccount(String accountName) throws Exception{
        for (Account anAccountsList : accountsList) {
            if (anAccountsList.getName().equals(accountName)) {
                return anAccountsList;
            }
        }
        throw new AccountNotExistException();
    }

    public void addAccount(String accountName) throws Exception{
       if (isExistedAccountName(accountName)) {
           throw new AccountNameNotUniqueException();
       }else {
           accountsList.add(new Account(accountName));
       }
    }

    public double getTotalBalance(){
        double totalBalance = 0.0;
        for (Account account: accountsList){
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public boolean isExistedAccountName(String accountName){
        for (Account anAccountsList : accountsList) {
            if (anAccountsList.getName().equals(accountName)) {
                return true;
            }
        }
        return false;
    }

    public void transfer(String account1, String account2, double money) throws Exception {
        if (this.getAccount(account1).getBalance() - money <= 0.0){
            throw new OverTransferException();
        }else {
            this.getAccount(account1).setBalance(this.getAccount(account1).getBalance() - money);
            this.getAccount(account2).setBalance(this.getAccount(account2).getBalance() + money);
        }
    }

}
