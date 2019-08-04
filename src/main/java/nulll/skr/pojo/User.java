package nulll.skr.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user")
public class User implements Comparable<User>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="userName")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;

    private Integer gender;
    private Image headPortrait;
    private String personalProfile;
    private Integer attentionNum;
    private Integer fansNum;
    private Date birthday;

    public Integer getAttentionNum() {
        return attentionNum;
    }
    public void setAttentionNum(Integer attentionNum) {
        if(attentionNum<0){
            this.attentionNum=0;
        }
        this.attentionNum = attentionNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }
    public void setFansNum(Integer fansNum) {
        if(fansNum<0){
            this.fansNum=0;
        }
        this.fansNum = fansNum;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }
    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public Image getHeadPortrait() {
        return headPortrait;
    }
    public void setHeadPortrait(Image headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(User o){
        return -1;
    }
}
