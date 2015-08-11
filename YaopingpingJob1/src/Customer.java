import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ppyao on 8/11/15.
 */
public class Customer {
        private String nickname;
        private Date dateofBirth1;
        public Customer(String nickname,String dateofBirth)throws ParseException {
            if (validateNickname(nickname)) {
                this.nickname = nickname;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                this.dateofBirth1 = formatter.parse(dateofBirth);

            }
            else {
                System.out.println("the nickname is error");
            }
        }
        public String getNickname()
        {
            return  nickname;
        }

        public Date getDateofBirth()
        {
            return  dateofBirth1;
        }
        public  boolean validateNickname(String nickname)
        {
            final String strRegex="^[a-z0-9]+$";
            Pattern pattern=Pattern.compile(strRegex);
            Matcher matcher=pattern.matcher(nickname);
            return matcher.find();
        }
}
