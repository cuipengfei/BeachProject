import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenliu on 8/11/15.
 */
public class Bank {
    List<Customer> customerList = new LinkedList<>();

    void add(Customer _customer) {

        if (isValid(_customer)) {
            customerList.add(_customer);
            System.out.println("Add Succeed");
        }
        else {
            System.out.println("Add Failed");
        }
    }

    public boolean isValid(Customer _customer) {
        String reg = "^[a-z0-9]+$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(_customer.getNickName());
        Boolean isValidName = matcher.matches();
        Boolean isExistName = false;

        for(Customer customer: customerList) {
            if (customer.getNickName().equals(_customer.getNickName()) )
                isExistName = true;
        }

        if (!isValidName)
            return false;
        else if(isExistName)
            return false;
        else
            return true;

    }

}

