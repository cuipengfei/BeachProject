package bank.infrastructure;

import bank.domain.aggregator.Customer;
import external.MessageGateway;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessageSenderTest {
    @Test
    public void should_send_message_to_gateway_api() throws Exception {
        MessageGateway messageGateway = mock(MessageGateway.class);
        MessageSender messageSender = new MessageSender(messageGateway);

        Customer customer = new Customer("jakim", new Date());
        customer.email("jakim@bank.com");

        messageSender.sendMessage(customer, "welcome");

        verify(messageGateway).sendMessage("jakim@bank.com", "welcome");
    }
}