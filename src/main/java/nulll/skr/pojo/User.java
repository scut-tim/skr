package nulll.skr.pojo;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity
@Table(name="user")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User implements Comparable<User>{
    public User(){}
    public User(String userName, String password, String email, Integer gender,
                String headPortrait, String personalProfile, Integer attentionNum,
                Integer fansNum, Date birthday, Set<Comment> commentSet,
                Set<Post> postSet) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        if (gender == null) {
            this.gender = 1;// 默认1为男性
        }
        if(headPortrait == null) {
            this.headPortrait = null;//可以改为默认图片，先设为空
        }
        if(personalProfile == null) {
            this.personalProfile = "";
        }
        this.attentionNum = 0;
        this.fansNum = 0;
        if(birthday == null){
            this.birthday = new Date(String.valueOf(new SimpleDateFormat("2020-1-1")));
        }

        if(commentSet == null){
            this.commentSet = new HashSet<Comment>();
        }
        else
            this.commentSet = commentSet;

        if(postSet == null) {
            this.postSet = new HashSet<Post>();
        }
        else
            this.postSet = postSet;
    }

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

    @Column(name ="gender")
    private Integer gender;

    @Column(name = "headPortrait")
    private String headPortrait;


    @Column(name = "personalProfile")
    private String personalProfile;

    @Column(name = "attentionNum")
    private Integer attentionNum;

    @Column(name = "fansNum")
    private Integer fansNum;

    @Column(name = "birthday")
    private Date birthday;


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Comment> commentSet;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private Set<Post> postSet;


    @ManyToMany(targetEntity = Post.class)
    @JoinTable(name="user_post_like",
            joinColumns={@JoinColumn(name="user_post",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="post_user",referencedColumnName = "id")})
    private Set<Post> postsOfLike;


    public Integer getAttentionNum() {
        return attentionNum;
    }
    private void setAttentionNum(Integer attentionNum) {
        if(attentionNum<0){
            this.attentionNum=0;
        }
        this.attentionNum = attentionNum;
    }
    public void addAttentionNum(){
        setAttentionNum(this.attentionNum+1);
    }
    public void subAttentionNum(){
        setAttentionNum(this.attentionNum-1);
    }

    public Integer getFansNum() {
        return fansNum;
    }
    private void setFansNum(Integer fansNum) {
        if(fansNum<0){
            this.fansNum=0;
        }
        this.fansNum = fansNum;
    }
    public void addFansNum(){
        setFansNum(this.fansNum+1);
    }
    public void subFansNum(){
        setFansNum(this.fansNum-1);
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


    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }
    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }


    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
    }


    public void addPost(Post post){
        this.postSet.add(post);
    }
    public void deletePost(Post post){
        for(Iterator it = postSet.iterator(); it.hasNext();){
            Post temp = (Post) it.next();
            if(post.getId()==temp.getId())
                it.remove();
        }
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


    public Set<Post> getPostsOfLike() {
        return postsOfLike;
    }

    public void setPostsOfLike(Set<Post> postsOfLike) {
        this.postsOfLike = postsOfLike;
    }

    @Override
    public int compareTo(User o){
        return -1;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", personalProfile='" + personalProfile + '\'' +
                ", attentionNum=" + attentionNum +
                ", fansNum=" + fansNum +
                ", birthday=" + birthday +
                '}';
    }
}
