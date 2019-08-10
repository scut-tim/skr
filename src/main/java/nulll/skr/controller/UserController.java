package nulll.skr.controller;

import nulll.skr.pojo.Comment;
import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Date;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public boolean userRegister(User user){

        if(userRepository.findByUserName(user.getUserName())==null){
            userRepository.save(user);
            return true;
        }

        System.out.println("重复拉，用户名，别乱插入了");
        return false;


    }

    @PostMapping(value = "/userLogin",produces = "application/json")
    public Boolean userLogin(User user, HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse){

        System.out.println(user);
        User user1 = userRepository.findByUserName(user.getUserName());
        System.out.println(user1);

        if(user1 != null){

            if(user1.getPassword().equals(user.getPassword())){
                System.out.println(user1.getPassword());
                Cookie cookie = new Cookie("user",user1.getUserName());
                httpServletRequest.getSession().setAttribute("userPassword",user.getPassword());
                httpServletResponse.addCookie(cookie);
                return true;

            }

        }
        return false;
    }

    public boolean viewInformation(User user){
        User user1 = userRepository.getOne(user.getId());
        if(user1 != null){
            // 至于这些属性显示应该就看前端了吧....
            Integer id = user1.getId();
            String userName = user1.getUserName();
            String email = user1.getEmail();
            Integer gender = user1.getGender();
            byte[] headPortrait = user1.getHeadPortrait();
            String personalProfile = user1.getPersonalProfile();
            Integer attentionNum = user1.getAttentionNum();
            Integer fansNum = user1.getFansNum();
            Date birthday = user1.getBirthday();

            return true;
        }
        return false;
    }

    @PostMapping("/image")
    public Boolean upLoad(MultipartFile image){
        try {
            byte[] imageByte = image.getBytes();
            User user = new User();
            user.setId(1);
            user.setHeadPortrait(imageByte);
            userRepository.save(user);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;

    }

    @PutMapping("/user")
    public boolean updateUser(User user){
        if(userRepository.getOne(user.getId())!=null){
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }


    //评论
    public boolean makeComment(Comment com, Post postRel){
        Post temp = PostController.getPostRepository().getOne(postRel.getId());
        if(temp != null) {
            temp.addComment(com);
            //对帖子和评论两个仓库进行修改
            CommentController.getCommentRepository().save(com);
            PostController.getPostRepository().saveAndFlush(temp);
            return true;
        }
        return false;
    }
    public boolean deleteComment(Comment com, Post postRel){
        Post temp = PostController.getPostRepository().getOne(postRel.getId());
        if(temp != null) {
            temp.deleteComment(com);
            //对帖子和评论两个仓库进行修改
            CommentController.getCommentRepository().delete(com);
            PostController.getPostRepository().saveAndFlush(temp);
            return true;
        }
        return false;
    }

    //点赞
    public boolean makeLike(Post postRel){
        Post temp = PostController.getPostRepository().getOne(postRel.getId());
        if(temp != null) {
            temp.addLike();
            PostController.getPostRepository().saveAndFlush(temp);
            return true;
        }
        return false;
    }
    public boolean cancelLike(Post postRel){
        Post temp = PostController.getPostRepository().getOne(postRel.getId());
        if(temp != null) {
            temp.cancelLike();
            PostController.getPostRepository().saveAndFlush(temp);
            return true;
        }
        return false;
    }

}
