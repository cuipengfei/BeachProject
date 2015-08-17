package bank.domain.aggregator;

import bank.domain.exception.InvalidCustomerException;

import java.util.Date;

public class Customer {

    private final String nickname;
    private final Date birthday;

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
}
