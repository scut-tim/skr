package nulll.skr.controller;

import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import nulll.skr.repository.PostRepository;
import nulll.skr.repository.UserRepository;
import nulll.skr.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${skr.Post.imagePath}")
    private String postImagePath;

    @PostMapping("/post")
    public boolean posting(Post post, MultipartFile postImage,
                           String userName, String postDate,
                           HttpServletRequest request) {

        System.out.println("=========/post=====");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            post.setDate(simpleDateFormat.parse(postDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //处理图片，将图片保存在skr.Post.imagePath所指定的位置
        String image =  fileUploadUtils.uploadFile(postImage,postImagePath);

        post.setImage(image);

        User user = userRepository.findByUserName(userName);

        post.setAuthor(user);

        postRepository.save(post);

        return true;
    }



    @GetMapping("/posts/{pageNum}")
    public List<Post> listPosts(@PathVariable(name="pageNum")int pageNum){
        Pageable pageable = new PageRequest(pageNum,4);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> postList = postPage.getContent();
        System.out.println(postList);
        return postList;
    }


    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable(name="id")int id){

        Post post = postRepository.getOne(id);
        return post;

    }



    @PutMapping("/post")
    public boolean updatePost(Post post){
        postRepository.saveAndFlush(post);
        return true;
    }

    @DeleteMapping("/post/{id}")
    public boolean deletePost(@PathVariable(name = "id")int id){
        Post post = postRepository.getOne(id);
        if(post != null){
            postRepository.delete(post);
            return true;
        }
        else
            return false;
    }

    @GetMapping("/searchedPost")
    public List<Post> searchPost(String keyWord){




        return null;

    }

}
