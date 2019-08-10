package nulll.skr.controller;

import nulll.skr.pojo.Comment;
import nulll.skr.pojo.User;
import nulll.skr.pojo.Post;
import nulll.skr.repository.CommentRepository;
import nulll.skr.repository.PostRepository;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

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
    public boolean addComment(Comment comment,String userName,int postId){

        Post post = postRepository.getOne(postId);

        Set<Comment> commentSet = post.getCommentSet();

        User user = userRepository.findByUserName(userName);

        comment.setUser(user);

        System.out.println(comment);

        commentRepository.save(comment);

        commentSet.add(comment);

        post.setCommentSet(commentSet);

        postRepository.saveAndFlush(post);

        return true;
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
