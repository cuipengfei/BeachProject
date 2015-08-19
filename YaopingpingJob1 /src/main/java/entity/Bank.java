package entity;

import email.MessageGateway;
import exception.OverdraftException;
import handle.CustomerHandler;
import handle.DespoitHandler;
import handle.WithdrawHandler;
import request.CustomerRequest;
import request.RequestType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ppyao on 8/12/15.
 */
public class Bank {
    public MessageGateway messageGateway;
    public LinkedList<Customer> customerLinkedList = new LinkedList<Customer>();
    public BankManager bankManager = new BankManager();
    public static Map<RequestType, CustomerHandler> customerHandlerMap = new HashMap<RequestType, CustomerHandler>();

    public Bank(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    static {
        customerHandlerMap.put(RequestType.depositMoney, new DespoitHandler());
        customerHandlerMap.put(RequestType.withdrawMoney, new WithdrawHandler());
    }

    public boolean AddCustomertoBankwhenValidCustomer(Customer customer) {
        if (validateNickname(customer) && isCustomerNotRepeat(customer)) {
            customerLinkedList.add(customer);

            String message = "Dear <" + customer.getNickname() + ">,Welcome to the Bank";
            messageGateway.sendEmail(customer.getEmailAddress(), message);
            customer.setJoinBankDay(new Date());
            return true;
        }
        return false;
    }

    public void handleRequest(CustomerRequest request) throws OverdraftException {

        if (customerLinkedList.contains(request.getCustomer())) {
            customerHandlerMap.get(request.getType()).handlers(request);
            if (isPrminumCustomer(request.getCustomer())) {
                messageGateway.sendEmail(bankManager.getEmailAddress(), request.getCustomer().getNickname() + " is a premium customer");
                bankManager.getPrminumCustomerList().add(request.getCustomer());
                request.getCustomer().setIsPreminumDefault(true);
            }
        }
    }

    private boolean isCustomerNotRepeat(Customer customer) {
        for (Customer customer1 : customerLinkedList) {
            if (customer.getNickname().equals(customer1.getNickname())) {
                return false;
            }
        }
        return true;
    }

  private boolean validateNickname(Customer customer) {
        final String strRegex = "^[a-z0-9]+$";
        Pattern pattern = Pattern.compile(strRegex);
        Matcher matcher = pattern.matcher(customer.getNickname());
        return matcher.find();
    }
    private boolean validateNickname(ApplicationList application)
    {
        final String strRegex = "^[a-z0-9]+$";
        Pattern pattern = Pattern.compile(strRegex);
        Matcher matcher = pattern.matcher(application.getNickname());
        return matcher.find();
    }

    private boolean isPrminumCustomer(Customer customer) {
        if (customer.getAccount().getBalance() >= 40000 && !(customer.isPreminum())) {
            return true;
        }
        return false;

    }
}
