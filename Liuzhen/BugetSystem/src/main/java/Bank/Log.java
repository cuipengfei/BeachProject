package Bank;

import Customer.Customer;
import mailsender.MailSender;
import mailsender.MailSenderStatusType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public void logCustomerMessage(MailSender mailSender, Customer customer) {
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
}
