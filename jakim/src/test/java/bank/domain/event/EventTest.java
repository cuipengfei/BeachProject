package bank.domain.event;

import bank.domain.aggregator.Customer;
import bank.domain.event.handler.Handler;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static bank.domain.event.Event.customerAddedEvent;
import static bank.domain.event.Event.fire;
import static bank.domain.event.Event.newPremiumEvent;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventTest {

    private Handler handler;

    @Before
    public void setUp() throws Exception {
        handler = mock(Handler.class);
        Event.addHandler(handler);
    }

    @Test
    public void should_raise_customer_events() throws Exception {
        Event event = customerAddedEvent(new Customer("jakim", new Date()));

        when(handler.shouldHandle(event)).thenReturn(true);

        fire(event);

        verify(handler).handle(event);
    }

    @Test
    public void should_raise_new_premium_event() throws Exception {
        Event event = newPremiumEvent(new Customer("jakim", new Date()));

        when(handler.shouldHandle(event)).thenReturn(true);

        fire(event);

        verify(handler).handle(event);
    }
}