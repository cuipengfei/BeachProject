import java.util.LinkedList;

/**
 * Created by ppyao on 8/11/15.
 */
public class Bank {

        LinkedList<Customer> customerLinkedList=new LinkedList<Customer>();
        private Customer customer;
        public void addCustomertoBank(Customer customer)
        {
            if(customer.getNickname()!=null) {
                if (this.isCustomerRepeat(customer)) {
                    customerLinkedList.add(customer);
                    System.out.println("add success");
                } else {
                    System.out.println("the nickname is exist");
                }
            }
            else
            {
                System.out.print("the nickname is error");
            }
        }

        public  boolean isCustomerRepeat(Customer customer)
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
    }

