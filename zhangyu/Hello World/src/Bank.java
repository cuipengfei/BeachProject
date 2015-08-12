import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhang on 8/11/15.
 */
public class Bank {
    List<Customer> customers = new ArrayList<Customer>();

    private boolean isValidNickname(Customer customer ){
        String nickname = customer.getNickname();
        return (nickname.matches("^[a-z0-9]+$"));
    }

    private boolean isRepeative(Customer customer) {
        for (Customer c: this.customers) {
            if(c.getNickname().equals(customer.getNickname())){
                return true;
            }
        }
        return false;
    }

    public String addToBank(Customer customer) {
        if (isValidNickname(customer) && !isRepeative(customer)) {
            this.customers.add(customer);
            customer.needAccount = true;
            return "add successful";
        } else {
            return "add failed";
        }
    }

}
