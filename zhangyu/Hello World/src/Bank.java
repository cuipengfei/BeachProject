import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhang on 8/11/15.
 */
public class Bank {
    List<Customer> customers = new ArrayList<Customer>();

    public boolean isValidNickname(Customer customer ){
        String nickname = customer.getNickname();
        return (nickname.matches("^[a-z0-9]+$"));
    }

    public boolean isRepeative(Customer customer) {
        for (Customer c: this.customers) {
            if(c.getNickname().equals(customer.getNickname())){
                return true;
            }
        }
        return false;
    }

    public void addToBank(Customer customer) {
        if(isValidNickname(customer) && !isRepeative(customer)) {
            System.out.println(customer.getNickname()+" , add successful!");
            this.customers.add(customer);
        }else{
            System.out.print(customer.getNickname()+" , add failed");
            if(!isValidNickname(customer))
                System.out.println(" , nickname should contain only lowercase letters or digits");
            else
                System.out.println(" , nickname already exits");
        }
    }

}
