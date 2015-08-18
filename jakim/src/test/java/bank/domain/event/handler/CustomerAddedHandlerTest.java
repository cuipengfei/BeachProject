package bank.domain.event.handler;

import bank.domain.aggregator.Customer;
import external.MessageGateway;
import org.junit.Test;

import java.util.Date;

import static bank.domain.event.Event.customerAddedEvent;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CustomerAddedHandlerTest {
    @Test
    public void should_send_message_to_manager_email_when_customer_beccome_premium() throws Exception {
        CustomerAddedEventHandler eventHandler = new CustomerAddedEventHandler();
        MessageGateway messageGateway = mock(MessageGateway.class);

        eventHandler.setMessageGateway(messageGateway);

        Customer customer = new Customer("jakim", new Date());
        customer.email("jakimli@bank.com");

        eventHandler.handle(customerAddedEvent(customer));

        verify(messageGateway).sendMessage("jakimli@bank.com", "Dear jakim, welcome to the bank");
    }
}
