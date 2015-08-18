package bank.domain.event.handler;

import bank.domain.aggregator.Customer;
import bank.domain.event.Event;
import bank.domain.event.EventType;
import external.MessageGateway;

import static java.lang.String.format;

public class NewPremiumEventHandler implements Handler {
    private MessageGateway messageGateway;

    @Override
    public void handle(Event event) {
        Customer customer = (Customer) event.getArgument();
        messageGateway.sendMessage("manager@bank.com", buildPremiumMessage(customer.nickname()));
    }

    @Override
    public boolean shouldHandle(Event event) {
        return event.getEventType() == EventType.NewPremium;
    }

    private String buildPremiumMessage(String nickname) {
        return format("%s is now a premium customer", nickname);
    }

    public void setMessageGateway(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }
}
