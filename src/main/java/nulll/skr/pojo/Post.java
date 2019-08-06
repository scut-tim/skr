package nulll.skr.pojo;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;

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
    private BufferedImage image;

    @Column(name="date")
    private Date date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="post_user",referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(String s) { ;
        try {
            this.image = ImageIO.read(new URL(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
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


}
