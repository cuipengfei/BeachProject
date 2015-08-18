package bank.domain.event.handler;

import bank.domain.aggregator.Customer;
import bank.domain.event.Event;
import bank.domain.event.EventType;
import external.MessageGateway;

import static java.lang.String.format;

public class CustomerAddedEventHandler implements Handler {

    private MessageGateway messageGateway;

    @Override
    public void handle(Event event) {
        Customer customer = (Customer) event.getArgument();
        messageGateway.sendMessage(customer.email(), buildWelcomeMessage(customer.nickname()));
    }

    @Override
    public boolean shouldHandle(Event event) {
        return event.getEventType() == EventType.CustomerAdded;
    }

    private String buildWelcomeMessage(String nickname) {
        return format("Dear %s, welcome to the bank", nickname);
    }

    public void setMessageGateway(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }
}
