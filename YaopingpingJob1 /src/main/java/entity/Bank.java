package entity;

import email.MessageGateway;
import exception.OverdrawException;
import handle.CustomerHandler;
import handle.DespoitHandler;
import handle.WithdrawHandler;
import request.CustomerRequest;
import request.RequestType;

import java.util.*;


/**
 * Created by ppyao on 8/12/15.
 */
public class Bank {
    public MessageGateway messageGateway;
    public List<Customer> customers = new LinkedList<Customer>();
    public BankManager bankManager = new BankManager();
    public static Map<RequestType, CustomerHandler> customerHandlerMap = new HashMap<RequestType, CustomerHandler>();

    public Bank(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    static {
        customerHandlerMap.put(RequestType.depositMoney, new DespoitHandler());
        customerHandlerMap.put(RequestType.withdraw, new WithdrawHandler());
    }

    public boolean addCustomer(Customer customer) {
        if (!isCustomerRepeated(customer)) {
            customers.add(initCustomer(customer));
            sendWelcomeMessage(customer);
            return true;
        }
        return false;
    }

    private void sendWelcomeMessage(Customer customer) {
        String message = String.format("Dear <%s>,Welcome to the Bank", customer.getNickname());
        messageGateway.sendEmail(customer.getEmailAddress(), message);
    }

    private Customer initCustomer(Customer customer) {
        customer.setAccount(new Account());
        customer.setJoinBankDay(Calendar.getInstance());
        return customer;
    }

    public void handleRequest(CustomerRequest request) throws OverdrawException {

        if (customers.contains(request.getCustomer())) {
            customerHandlerMap.get(request.getType()).handle(request);

            if (isPrminumCustomer(request.getCustomer())) {
                messageGateway.sendEmail(bankManager.getEmailAddress(), request.getCustomer().getNickname() + " is a premium customer");
                request.getCustomer().setIsPreminumDefault(true);

            }
        }
    }

    private boolean isCustomerRepeated(Customer searchingCustomer) {
        return customers.stream()
                .filter(customer ->
                        customer.getNickname().equals(searchingCustomer.getNickname())
                )
                .findFirst().isPresent();
    }

    private boolean isPrminumCustomer(Customer customer) {
        if (customer.getAccount().getBalance() >= 40000 && !(customer.isPreminum())) {
            return true;
        }
        return false;

    }
}
