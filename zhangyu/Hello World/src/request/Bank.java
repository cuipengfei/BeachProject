package request;

import handle.Handlers;
import handle.Account;

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

    private boolean isValidEmailAddress(Customer customer) {
        String emailAdress = customer.getEmailAddress();
        return emailAdress.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }

    public String addToBank(Customer customer) {
        if (isValidNickname(customer) && !isRepeative(customer)) {
            this.customers.add(customer);
            customer.setMyAccount(new Account());
            sendEmail(customer);
            return "add successful";
        } else {
            return "add failed";
        }
    }

    public int handleRequest(CustomerRequest request) throws Exception {
        Handlers.findHandler(request.getType()).handle(request);
        return request.getCustomer().getMyAccount().getBalance();
    }

    public void sendEmail(Customer customer){
        if(isValidEmailAddress(customer)){
            customer.setEmailContent("Dear "+customer.getNickname()+" , Welcome to bank");
        }
    }

}
