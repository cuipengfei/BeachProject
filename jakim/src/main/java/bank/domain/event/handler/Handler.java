package bank.domain.event.handler;

import bank.domain.event.Event;

public interface Handler {
    void handle(Event event);

    boolean shouldHandle(Event event);
}
