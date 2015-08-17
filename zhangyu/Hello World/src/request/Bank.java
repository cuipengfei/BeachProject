package request;

import handle.Handlers;
import handle.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzhang on 8/11/15.
 */
public class Bank {
    EmailSend sender;
    List<Customer> customers = new ArrayList<Customer>();

    public Bank(EmailSend sender) {
        this.sender = sender;
    }

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
            customer.setMyAccount(new Account());
            customer.setMyMailBox(new MailBox());
            sender.sendEmail(customer);
            return "add successful";
        } else {
            return "add failed";
        }
    }

    public int handleRequest(CustomerRequest request) throws Exception {
        Handlers.findHandler(request.getType()).handle(request);
        return request.getCustomer().getMyAccount().getBalance();
    }
}
