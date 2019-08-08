package nulll.skr.pojo;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "likeNum")
    private Integer likeNum;

    @Column(name="date")
    private Date date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="post_author",referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> commentSet;

    @ManyToOne(targetEntity = Snack.class)
    @JoinColumn(name="post_snack",referencedColumnName = "id")
    private Snack snack;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
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
}
