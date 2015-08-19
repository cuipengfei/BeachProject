package entity;

import java.util.Date;

/**
 * Created by ppyao on 8/19/15.
 */
public class ApplicationList {
    private String nickname;
    private Date birthday;

    public ApplicationList(String nickname, Date birthday) {
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
