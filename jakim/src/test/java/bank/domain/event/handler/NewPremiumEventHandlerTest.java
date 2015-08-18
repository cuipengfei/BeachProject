package bank.domain.event.handler;

import bank.domain.aggregator.Customer;
import external.MessageGateway;
import org.junit.Test;

import java.util.Date;

import static bank.domain.event.Event.newPremiumEvent;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewPremiumEventHandlerTest {
    @Test
    public void should_send_message_to_manager_email_when_customer_beccome_premium() throws Exception {
        NewPremiumEventHandler eventHandler = new NewPremiumEventHandler();
        MessageGateway messageGateway = mock(MessageGateway.class);

        eventHandler.setMessageGateway(messageGateway);

        Customer customer = new Customer("jakim", new Date());
        customer.email("jakimli@bank.com");

        eventHandler.handle(newPremiumEvent(customer));

        verify(messageGateway).sendMessage("manager@bank.com", "jakim is now a premium customer");

    }
}