package bank.domain.event;

import bank.domain.aggregator.Customer;
import bank.domain.event.handler.Handler;

import java.util.ArrayList;
import java.util.List;

import static bank.domain.event.EventType.CustomerAdded;
import static bank.domain.event.EventType.NewPremium;

public class Event {

    private static List<Handler> handlers = new ArrayList<>();
    private Object argument;
    private EventType eventType;

    public Event(EventType eventType, Object argument) {
        this.eventType = eventType;
        this.argument = argument;
    }

    public static void fire(Event event) {
        handlers.stream().forEach(handler -> {
            if (handler.shouldHandle(event)) {
                handler.handle(event);
            }
        });
    }

    public static void addHandler(Handler customerEventHandler) {
        handlers.add(customerEventHandler);
    }

    public EventType getEventType() {
        return eventType;
    }

    public static Event customerAddedEvent(Customer customer) {
        return new Event(CustomerAdded, customer);
    }

    public Object getArgument() {
        return argument;
    }

    public static Event newPremiumEvent(Customer customer) {
        return new Event(NewPremium, customer);
    }
}
