import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppyao on 8/11/15.
 */
public class Bank
{
    LinkedList<Customer> customerLinkedList=new LinkedList<Customer>();
    public boolean isAddCustomertoBank(Customer customer)
        {
           if(validateNickname(customer)&&isCustomerNotRepeat(customer))
            {
                customerLinkedList.add(customer);
                return true;
            }
            return false;

        }
        private   boolean isCustomerNotRepeat(Customer customer)
        {
            for(Customer customer1:customerLinkedList)
            {

                if(customer1.getNickname().equals(customer.getNickname()))
                {
                    return false;

                }

            }
            return true;
        }
       private   boolean validateNickname(Customer customer )
       {
           final String strRegex="^[a-z0-9]+$";
           Pattern pattern=Pattern.compile(strRegex);
           Matcher matcher=pattern.matcher(customer.getNickname());
           return matcher.find();
       }


    }

