package nulll.skr.controller;

import nulll.skr.pojo.Comment;
import nulll.skr.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/comment")
    public boolean addComment(Comment comment){
        commentRepository.save(comment);
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
