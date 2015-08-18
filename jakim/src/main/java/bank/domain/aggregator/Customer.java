package bank.domain.aggregator;

import bank.domain.exception.InvalidCustomerException;
import bank.domain.exception.InvalidEmailAddressExcpetion;

import java.util.Date;

public class Customer {

    private final String nickname;
    private final Date birthday;
    private final Account account = new Account();
    private String email;
    private boolean premium;

    public Customer(String nickname, Date birthday) {
        if (!hasBirthDay(birthday) || !isValidName(nickname)) {
            throw new InvalidCustomerException("Invalid customer");
        }
        this.nickname = nickname;
        this.birthday = birthday;
    }

    private boolean isValidName(String nickname) {
        return nickname.matches("^[a-z0-9]+$");
    }

    private boolean hasBirthDay(Date birthday) {
        return birthday != null;
    }

    public String nickname() {
        return this.nickname;
    }

    public Account getAccount() {
        return account;
    }

    public String email() {
        return this.email;
    }

    public void email(String email) {
        if (!email.matches("^[a-z0-9]+@[a-z]+\\.[a-z]+$")) {
            throw new InvalidEmailAddressExcpetion();
        }
        this.email = email;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
