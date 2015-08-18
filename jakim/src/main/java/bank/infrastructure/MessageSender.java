package bank.infrastructure;

import bank.domain.aggregator.Customer;
import external.MessageGateway;
import external.StandardMessageGateway;

public class MessageSender {

    MessageGateway messageGateway;

    public MessageSender(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    public void sendMessage(Customer customer, String message) {
        messageGateway.sendMessage(customer.email(), message);
    }
}
