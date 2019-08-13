package nulll.skr.controller;

import nulll.skr.pojo.Comment;
import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import nulll.skr.repository.CommentRepository;
import nulll.skr.repository.PostRepository;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

//    @Autowired
//    private CommentRepository commentRepository;

    @PostMapping("/user")
    public boolean userRegister(User user,HttpServletRequest request){

        if(userRepository.findByUserName(user.getUserName())==null){
            request.getSession().setAttribute("LoginUser",user.getUserName());
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
                Cookie cookie1 = new Cookie("user",user1.getUserName());
                Cookie cookie2 = new Cookie("userId",String.valueOf(user1.getId()));
                httpServletRequest.getSession().setAttribute("LoginUser",user.getUserName());
                httpServletResponse.addCookie(cookie1);
                httpServletResponse.addCookie(cookie2);
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
            //byte[] headPortrait = user1.getHeadPortrait();
            String personalProfile = user1.getPersonalProfile();
            Integer attentionNum = user1.getAttentionNum();
            Integer fansNum = user1.getFansNum();
            Date birthday = user1.getBirthday();

            return true;
        }
        return false;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable(name = "id")int id){

        User user = userRepository.getOne(id);
        if(user != null){
            user.setPostSet(postRepository.findAllByAuthor(user));
            return user;
        }
        return null;
    }


    @PostMapping("/headPortrait/{id}")
    public Boolean upLoad(MultipartFile image,@PathVariable(name="id")int id){
//        try {
//            byte[] imageByte = image.getBytes();
//            User user = userRepository.getOne(id);
//            user.setHeadPortrait(imageByte);
//            userRepository.save(user);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//
         return true;

    }

    @PutMapping("/user")
    public boolean updateUser(User user,MultipartFile putHeadPortrait,
                              String putBirthday){


        System.out.println("putHeadPortrait: "+putHeadPortrait);


        if(userRepository.findByUserName(user.getUserName())!=null){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                user.setBirthday(simpleDateFormat.parse(putBirthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            System.out.println(user);

            //userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }


//应该是由CommentController负责吧

//    //评论
//    public boolean makeComment(Comment com, Post postRel){
//        Post temp = postRepository.getOne(postRel.getId());
//        if(temp != null) {
//            temp.addComment(com);
//            //对帖子和评论两个仓库进行修改
//            commentRepository.save(com);
//            postRepository.saveAndFlush(temp);
//            return true;
//        }
//        return false;
//    }
//    public boolean deleteComment(Comment com, Post postRel){
//        Post temp = postRepository.getOne(postRel.getId());
//        if(temp != null) {
//            temp.deleteComment(com);
//            //对帖子和评论两个仓库进行修改
//            commentRepository.delete(com);
//            postRepository.saveAndFlush(temp);
//            return true;
//        }
//        return false;
//    }


// 应该是由PostController负责吧

//    //点赞
//    public boolean makeLike(Post postRel){
//        Post temp = postRepository.getOne(postRel.getId());
//        if(temp != null) {
//            temp.addLike();
//            postRepository.saveAndFlush(temp);
//            return true;
//        }
//        return false;
//    }
//    public boolean cancelLike(Post postRel){
//        Post temp = postRepository.getOne(postRel.getId());
//        if(temp != null) {
//            temp.cancelLike();
//            postRepository.saveAndFlush(temp);
//            return true;
//        }
//        return false;
//    }

}
