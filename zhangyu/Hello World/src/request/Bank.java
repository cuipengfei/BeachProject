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
    private Manager manager = new Manager();

    public Manager getManager() {return manager;}

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
            sender.setContent("Dear " + customer.getNickname() + " , Welcome to bank");
            sender.sendEmail(customer);
            return "add successful";
        } else {
            return "add failed";
        }
    }

    public int handleRequest(CustomerRequest request) throws Exception {
        Customer customer = request.getCustomer();
        Handlers.findHandler(request.getType()).handle(request);

        if( customer.getMyAccount().getBalance()>40000 && (customer.isPremium == false)){
            customer.isPremium = true;
            sender.setContent(customer.getNickname() + " is now a premium customer");
            sender.sendEmail(manager);
        }

        return customer.getMyAccount().getBalance();
    }

}
