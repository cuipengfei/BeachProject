package Bank;

import Customer.Customer;
import Handler.Handlers;
import mailsender.MailSender;
import mailsender.MailSenderStatusType;
import MyException.CustomerNotExistException;
import Request.CustomerRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
                mailSender.sendEmail("manager@thebank.com", _request.getCustomer() + " is now a premium customer.");
                _request.getCustomer().setIsPremiumCustomer(true);
            }
        }
        else throw new CustomerNotExistException();
    }



    private void sendWelcomeMessage(Customer _customer){
        mailSender.sendEmail( _customer.getNickName() + "@thebank.com", "Dear " + _customer.getNickName() + ", Welcome to the Bank!");
        logCustomerMessage(mailSender,_customer);
    }
    //write log into file
    private void logCustomerMessage(MailSender mailSender, Customer customer) {
        MailSenderStatusType status = mailSender.getStatus();
        try {
            File file = new File("/Users/zhenliu/Public/TWTraining/BeachProject/Liuzhen/BugetSystem/src/main/Log/CustomerMessageLog");
            if (!file.exists()) {
                file.createNewFile();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("-----------").append(simpleDateFormat.format(new Date())).append("------------\n");
            switch (status) {
                case OK:
                    stringBuilder.append("Mail sender's status is OK when send message to ").append(customer.getNickName()).append("\n");
                    break;
                case FAILED:
                    stringBuilder.append("Mail sender's status is FAILED when send message to ").append(customer.getNickName()).append("\n");
                    break;
                case EXCEPTION:
                    stringBuilder.append("Mail sender's status is EXCEPTION when send message to ").append(customer.getNickName()).append("\n");
            }
            fileOutputStream.write(stringBuilder.toString().getBytes("utf-8"));
            fileOutputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
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

