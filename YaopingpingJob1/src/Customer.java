import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppyao on 8/11/15.
 */
public class Customer {
        private  Account account;
        private String nickname;
        private Date dateofBirth1;

        public Customer(String nickname,Date dateofBirth)throws ParseException {
                this.nickname = nickname;
                this.dateofBirth1=dateofBirth;

        }
        public String getNickname()
        {
            return  nickname;
        }

        public Date getDateofBirth()
        {
            return  dateofBirth1;
        }

}
