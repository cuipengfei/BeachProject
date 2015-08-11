import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhang on 8/11/15.
 */
public class Bank {
    List<Customer> customers = new ArrayList<Customer>();

    public boolean isValidNickname(Customer customer ){
        String nickname = customer.getNickname();
        return nickname.matches("^[a-z0-9]+$");
    }

    public boolean isRepeative(Customer customer) {
        for (Customer c: this.customers) {
            if(c.getNickname().equals(customer.getNickname())){
                return true;
            }
        }
        return false;
    }

    public boolean isAdd(Customer customer){
        if(isValidNickname(customer) && !isRepeative(customer)) {
            System.out.println(customer.getNickname()+" , add successful!");
            return true;
        }else{
            System.out.println(customer.getNickname()+" , add failed!");
            return false;
        }
    }

    public void addToBank(Customer customer) {
        this.customers.add(customer);
    }

}
