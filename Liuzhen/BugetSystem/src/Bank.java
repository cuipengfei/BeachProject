import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhenliu on 8/11/15.
 */
public class Bank {
    List<Customer> customerList = new LinkedList<>();

    public boolean add(Customer _customer) {
        boolean shouldAddCustomer = shouldAdd(_customer);

        if (shouldAddCustomer) {
            _customer.setAccount(0.0);
            customerList.add(_customer);
        }
        if(shouldAddCustomer)
            System.out.println("shouldAdd");
        else
            System.out.println("shouldNotAdd");

            return shouldAddCustomer;

    }

    public void withdrawAll(Customer _customer) throws Exception {
        int index = find(_customer);
        double currentMoneyInAccount = customerList.get(index).getAccount();
        if (index != -1) {
            if(currentMoneyInAccount > 0.0) {
                _customer.setAccount(0.0);
            }
            else throw new Exception("No Enough Money in Account!");
        }
        else throw new Exception("The customer is not exist in the bank!");
    }

    public void withdraw(Customer _customer,double _moneyWillBeDrawn) throws Exception {
        int index = find(_customer);
        double currentMoneyInAccount = _customer.getAccount();
        if (index != -1) {
            if(_moneyWillBeDrawn < currentMoneyInAccount) {
                _customer.setAccount(currentMoneyInAccount - _moneyWillBeDrawn);
            }
            else throw new Exception("No Enough Money in Account!");
        }
        else throw new Exception("The customer is not exist in the bank!");

    }

    public void deposit(Customer _customer,double _moneyWillBeDeposited) throws Exception {
        int index = find(_customer);
        double currentMoneyInAccount = _customer.getAccount();
        if (index != -1) {
            _customer.setAccount(currentMoneyInAccount +_moneyWillBeDeposited);
        }
        else throw new Exception("The customer is not exist in the bank!");

    }

    protected int find(Customer _customer) {
        int index = 0;
        for(Customer customer:customerList){
            if (customer.getNickName().equals(_customer.getNickName()))
                return index;
            index++;
        }
        return -1;
    }

    private boolean shouldAdd(Customer _customer) {
        boolean isNotValidNickName = (_customer != Customer.getInvalidCustomer());
        boolean isExistName = isExistName(_customer);
        return  isNotValidNickName && !isExistName;
    }

    public boolean isExistName(Customer _customer) {
        boolean isExistName = false;
        for(Customer customer: customerList) {
            if (customer.getNickName().equals(_customer.getNickName()) )
                isExistName = true;
        }
        return isExistName;
    }

}

