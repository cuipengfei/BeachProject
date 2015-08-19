package request;

import email.MessageGateway;
import handle.Handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank {
    MessageGateway sender;
    List<Customer> customers = new ArrayList<Customer>();
    private Manager manager = new Manager();

    public Manager getManager() {return manager;}

    public Bank(MessageGateway sender) {
        this.sender = sender;
    }

    private boolean isValidNickname(Customer customer) {
        String nickname = customer.getNickname();
        return (nickname.matches("^[a-z0-9]+$"));
    }

    private boolean isRepeative(Customer customer) {
        for (Customer c : customers) {
            if (c.getNickname().equals(customer.getNickname())) {
                return true;
            }
        }
        return false;
    }

    private boolean isPremiumCustomer(Customer customer) {
        return customer.getMyAccount().getBalance() >= 40000 && !customer.isPremium();
    }

    public boolean addToBank(Customer customer) {
        if (isValidNickname(customer) && !isRepeative(customer)) {
            customers.add(customer);
            customer.setDateOfJoin(new Date());
            customer.setMyAccount(new Account());
            String content = "Dear " + customer.getNickname() + " , Welcome to bank";
            sender.sendEmail(customer.getEmailAddress(), content);
            return true;
        } else {
            return false;
        }
    }

    public int handleRequest(CustomerRequest request) throws Exception {
        Customer customer = request.getCustomer();
        Handlers.findHandler(request.getType()).handle(request);

        if (isPremiumCustomer(customer)) {
            customer.setIsPremium(true);
            String content = customer.getNickname() + " is now a premium customer";
            sender.sendEmail(manager.getEmailAddress(), content);
        }
        return customer.getMyAccount().getBalance();
    }
}
