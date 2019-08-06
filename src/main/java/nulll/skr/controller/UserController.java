package nulll.skr.controller;

import nulll.skr.pojo.User;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


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
    public Boolean userLogin(User user){

        System.out.println(user);
        User user1 = userRepository.findByUserName(user.getUserName());
        System.out.println(user1);

        if(user1 != null){

            if(user1.getPassword().equals(user.getPassword())){
                System.out.println(user1.getPassword());
                return true;
            }

        }
        return false;
    }

    @PostMapping("/image")
    public Boolean upLoad(MultipartFile image){

        //////
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

        ///////
    }






}
