package entity;

import email.MailSendStatus;
import email.MessageGateway;
import exception.OverdrawException;
import handle.CustomerHandler;
import handle.DepositHandler;
import handle.WithdrawHandler;
import request.CustomerRequest;
import request.RequestType;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bank {
    public MessageGateway messageGateway;
    public List<Customer> customers = new LinkedList<Customer>();
    public BankManager bankManager = new BankManager();
    public static Map<RequestType, CustomerHandler> customerHandler = new HashMap<RequestType, CustomerHandler>();

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
            writeMessageLog(customer);
            return true;
        }
        messageGateway.setSendStatus(MailSendStatus.FAILED);
        writeMessageLog(customer);
        return false;
    }

    public void handleRequest(CustomerRequest request) throws OverdrawException {

        if (customers.contains(request.getCustomer())) {
            customerHandler.get(request.getType()).handle(request);
            sendMessage(request.getCustomer());
        }
    }

    private void sendMessageToCustomer(Customer customer) {
        messageGateway.sendEmail(customer.getEmailAddress(), buildWelcomeMessage(customer.getNickname()));
        messageGateway.setSendStatus(MailSendStatus.OK);
    }

    private void sendMessage(Customer customer) {
        if (isPremiumCustomer(customer)) {
            String messageToManager = messageToManager(customer);
            messageGateway.sendEmail(bankManager.getEmailAddress(), messageToManager);
            customer.setPremiumDefault(true);
        }
    }

    private void writeMessageLog(Customer customer) {
        MailSendStatus status = messageGateway.getSendStatus();
        try {
            File file = new File("sendMessageLog");
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintStream printer = new PrintStream(new FileOutputStream(file, true));
            switch (status) {
                case OK:
                    printer.append(logMessage(status, customer));
                    break;
                case FAILED:
                    printer.append(logMessage(status, customer));
                    break;
                case EXCEPTION:
                    printer.append(logMessage(status, customer));
                    break;
                default:
            }
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String logMessage(MailSendStatus status, Customer customer) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd  hh:mm:ss a zzz");
        return String.format("mail send status is %s %s %s \n", status, dateFormat.format(new Date()), customer.getNickname());
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
        if (customer.getAccount().getBalance() >= 40000 && !(customer.isPremiumDefault())) {
            return true;
        }
        return false;
    }
}
