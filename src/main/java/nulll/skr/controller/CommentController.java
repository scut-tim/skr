package nulll.skr.controller;

import nulll.skr.pojo.Comment;
import nulll.skr.pojo.User;
import nulll.skr.pojo.Post;
import nulll.skr.repository.CommentRepository;
import nulll.skr.repository.PostRepository;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PostRepository postRepository;


    @PostMapping("/comment")
    public boolean addComment(Comment comment,String userName,
                              int postId, String commentDate){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            comment.setDate(simpleDateFormat.parse(commentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        comment.setUser(userRepository.findByUserName(userName));
        comment.setPost(postRepository.getOne(postId));

        System.out.println(comment);
        commentRepository.save(comment);

        return true;
    }

    @GetMapping("/comments/{pageNum}")
    public List<Comment> listComments(@PathVariable(name="pageNum")int pageNum){
        Pageable pageable = new PageRequest(pageNum,6);
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        List<Comment> commentList = commentPage.getContent();
        return commentList;
    }

    @PutMapping("/comment")
    public boolean updateComment(Comment comment){
        commentRepository.saveAndFlush(comment);
        return true;
    }

    @DeleteMapping("/comment/{id}")
    public boolean deleteComment(@PathVariable(name = "id")int id){
        Comment comment = commentRepository.getOne(id);
        if(comment != null){
            commentRepository.delete(comment);
            return true;
        }
        else
            return false;
    }



}
