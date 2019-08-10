package nulll.skr.pojo;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "snack")
public class Snack {
    public Snack(){}
    public Snack(String name, Integer price, Integer likeNum, byte[] image, Set<Post> postSet) {
        this.name = name;
        this.price = price;
        this.likeNum = likeNum;
        this.image = image;
        if(postSet == null) {
            this.postSet = new HashSet<Post>();
        }
        else
            this.postSet = postSet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "likeNum")
    private Integer likeNum;

    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "snack",cascade = CascadeType.ALL)
    private Set<Post> postSet;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLikeNum() {
        return likeNum;
    }
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
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
        this.postSet.remove(post);
    }
}
