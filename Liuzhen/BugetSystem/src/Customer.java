import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhenliu on 8/11/15.
 */
public class Customer {
    private String nickName;
    private Date dateOfBirth;

    public Customer(String _nickName, String _dateOfBirth) throws ParseException {
        nickName = _nickName;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateOfBirth = sdf.parse(_dateOfBirth);
    }

    public String getNickName(){
        return nickName;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }
}
