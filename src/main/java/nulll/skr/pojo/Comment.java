package nulll.skr.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "comment")
public class Comment{
    public Comment(){}


    public Comment(User user, String content, Date date, Post post) {
        this.user = user;
        this.content = content;
        this.date = new Date();
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @JsonIgnoreProperties("postSet")
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="comment_user",referencedColumnName = "id")
    private User user;

    @Column(name="content")
    private String content;

    @Column(name="date")
    private Date date;



    @JsonIgnore
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name="comment_post",referencedColumnName = "id")
    private Post post;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", post=" + post +
                '}';
    }
}
