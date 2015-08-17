package Handler;

import Request.CustomerRequest;
import Src.MailSender;

public interface RequestHandler {
     void handle(CustomerRequest request) throws Exception;
}
