package Bank;

import Customer.Customer;
import Handler.Handlers;
import MailSender.MailSender;
import MyException.CustomerNotExistException;
import Request.CustomerRequest;
import Request.RequestType;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Bank {
    private List<Customer> customerList = new LinkedList<>();
    private MailSender mailSender;
    public Bank(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    public boolean add(Customer _customer) {
        if (shouldAdd(_customer)) {
            customerList.add(_customer);
            _customer.setJoiningDate(Calendar.getInstance());
            sendWelcomeMessage(_customer);
        }

        return shouldAdd(_customer);
    }

    public void handleRequest(CustomerRequest _request) throws Exception{
        if (customerList.contains(_request.getCustomer())){
            Handlers.findHandler(_request.getRequestType()).handle(_request);

            if (_request.getCustomer().getAccount()>=40000.0 && !_request.getCustomer().isPremiumCustomer()) {
                mailSender.sendEmail( "manager@thebank.com",_request.getCustomer() + " is now a premium customer.");
                _request.getCustomer().setIsPremiumCustomer(true);
            }

            Calendar customerJoinDate = _request.getCustomer().getJoiningDate();
            Calendar dateOfToday = Calendar.getInstance();
            if ((_request.getCustomer().getTwoYearsBonus() == 0.0)
                    &&(_request.getRequestType().compareTo(RequestType.deposit)==0)
                    &&((customerJoinDate.get(Calendar.YEAR)+2) <= dateOfToday.get(Calendar.YEAR))
                    && (customerJoinDate.get(Calendar.MONTH) <= dateOfToday.get(Calendar.MONTH))
                    && (customerJoinDate.get(Calendar.DATE) <= dateOfToday.get(Calendar.DATE))){
                _request.getCustomer().setTwoYearsBonus(5.0);
                _request.getCustomer().setAccount(_request.getCustomer().getAccount()+5.0);
            }
        }
        else throw new CustomerNotExistException();
    }

    private void sendWelcomeMessage(Customer _customer){
        mailSender.sendEmail( _customer.getNickName() + "@thebank.com", "Dear " + _customer.getNickName() + ", Welcome to the Bank!");
    }

    private boolean shouldAdd(Customer _customer) {
        boolean isNotValidNickName = (_customer != Customer.getInvalidCustomer());
        boolean isExistName = isExistName(_customer);
        return  isNotValidNickName && !isExistName;
    }

    private boolean isExistName(Customer _customer) {
        boolean isExistName = false;
        for(Customer customer: customerList) {
            if (customer.getNickName().equals(_customer.getNickName()) )
                isExistName = true;
        }
        return isExistName;
    }

}

