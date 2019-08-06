package nulll.skr.pojo;


import javax.persistence.*;
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

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="post_user",referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comment;






}
