package nulll.skr.controller;

import nulll.skr.pojo.Post;
import nulll.skr.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;



@RestController
public class PostController {


    @Autowired
    private PostRepository postRepository;


    @PostMapping("/post")
    public boolean posting(Post post){

        ///////////////
        postRepository.save(post);
        return true;
        ///////////////

    }

    @GetMapping("/posts/{pageNum}")
    public List<Post> listPosts(@PathVariable(name="pageNum")int pageNum){


        Pageable pageable = new PageRequest(pageNum,10);

        Page<Post> postPage = postRepository.findAll(pageable);

        List<Post> postList = postPage.getContent();

        System.out.println(postList);

        return postList;

    }





}
