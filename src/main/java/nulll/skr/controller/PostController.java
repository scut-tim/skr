package nulll.skr.controller;

import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import nulll.skr.repository.PostRepository;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/post")
    public boolean posting(Post post,MultipartFile postImage,
                           String userName,String postDate){



        System.out.println("=========/post=====");

        System.out.println("first: "+post);


        System.out.println("date: "+postDate);

        System.out.println("image: "+postImage);

        System.out.println("userName: "+userName);



        try {
            post.setImage(postImage.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        User user = userRepository.findByUserName(userName);

        post.setAuthor(user);



        System.out.println("second: "+post);

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
}
