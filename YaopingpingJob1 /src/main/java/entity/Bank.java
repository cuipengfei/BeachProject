package entity;

import email.MailSendStatus;
import email.MessageGateway;
import exception.OverdrawException;
import handle.CustomerHandler;
import handle.DepositHandler;
import handle.WithdrawHandler;
import request.CustomerRequest;
import request.RequestType;
import utils.FileUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class Bank {
    public MessageGateway messageGateway;
    public List<Customer> customers = new LinkedList<Customer>();
    public BankManager bankManager = new BankManager();
    public static Map<RequestType, CustomerHandler> customerHandler = new HashMap<RequestType, CustomerHandler>();
    public MailSendStatus status;

    public Bank(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    static {
        customerHandler.put(RequestType.depositMoney, new DepositHandler());
        customerHandler.put(RequestType.withdraw, new WithdrawHandler());
    }

    public boolean addCustomer(Customer customer) {
        if (!isCustomerRepeated(customer)) {
            customers.add(initCustomer(customer));
            sendMessageToCustomer(customer);
            return true;
        }
        return false;
    }

    private void sendMessageToCustomer(Customer customer) {
        try {
            status = messageGateway.sendEmail(customer.getEmailAddress(), buildWelcomeMessage(customer.getNickname()));
        } catch (Exception e) {
            status = MailSendStatus.EXCEPTION;
        }
        FileUtils.writeMessageLog(buildDiary(status, customer));
    }

    public void handleRequest(CustomerRequest request) throws OverdrawException {

        if (customers.contains(request.getCustomer())) {
            customerHandler.get(request.getType()).handle(request);
            sendMessage(request.getCustomer());
        }
    }

    private void sendMessage(Customer customer) {
        if (isPremiumCustomer(customer)) {
            String messageToManager = messageToManager(customer);
            messageGateway.sendEmail(bankManager.getEmailAddress(), messageToManager);
            customer.setPremiumDefault(true);
        }
    }

    private String buildDiary(MailSendStatus status, Customer customer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  hh:mm:ss a zzz");
        return String.format("mail send  %s %s %s \n", status, dateFormat.format(new Date()), customer.getNickname());
    }

    private String buildWelcomeMessage(String nickname) {
        return String.format("Dear <%s>, Welcome to the Bank", nickname);
    }

    private String messageToManager(Customer customer) {
        return String.format("%s, is a premium customer", customer.getNickname());
    }

    private Customer initCustomer(Customer customer) {
        customer.setJoinBankDay(Calendar.getInstance());
        return customer;
    }

    private boolean isCustomerRepeated(Customer searchingCustomer) {
        return customers.stream()
                .filter(customer ->
                                customer.getNickname().equals(searchingCustomer.getNickname())
                )
                .findFirst().isPresent();
    }

    private boolean isPremiumCustomer(Customer customer) {
        for (int index = 0; index < customer.getAccounts().size(); index++) {
            if (customer.getAccounts().get(index).getBalance() >= 40000 && !(customer.isPremiumDefault())) {
                return true;
            }
        }
        return false;
    }
}
