package nulll.skr.pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")

public class Post {
    public Post(){}
    public Post(String title, String content, String image, Integer likeNum,
                Date date, User author, Set<Comment> commentSet, Snack snack) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.likeNum = 0;
        this.date = new Date();//创建时间即为当前日期
        this.author = author;
        if(commentSet == null) {
            this.commentSet = new HashSet<Comment>();
        }
        else
            this.commentSet = commentSet;
        this.snack = snack;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name = "image")
    private String image;

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    @Column(name = "likeNum")
    private Integer likeNum;

    @Column(name="date")
    private Date date;

    @JsonIgnoreProperties({"postSet","postsOfLike"})
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="post_author",referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> commentSet;

    @ManyToOne(targetEntity = Snack.class)
    @JoinColumn(name="post_snack",referencedColumnName = "id")
    private Snack snack;



    @ManyToMany(mappedBy = "postsOfLike",cascade = CascadeType.ALL)
    private Set<User> usersOfLike = new HashSet<>();

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }
    public Integer getLikeNum() {
        return likeNum;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }



    public Set<Comment> getCommentSet() {
        return commentSet;
    }
    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public void addComment(Comment comment){
        commentSet.add(comment);
    }
    public void deleteComment(Comment comment) {
         commentSet.remove(comment);
    }

    public void addLike(){
        this.likeNum++;
        this.snack.addLikeNum();
    }
    public void cancelLike(){
        this.likeNum--;
        this.snack.reduceLikeNum();
    }


    public Set<User> getUsersOfLike() {
        return usersOfLike;
    }

    public void setUsersOfLike(Set<User> usersOfLike) {
        this.usersOfLike = usersOfLike;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", author=" + author +
                '}';
    }
}
