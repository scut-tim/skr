package nulll.skr.controller;

import nulll.skr.pojo.User;
import nulll.skr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user")
    public void userRegister(User user){

        userRepository.save(user);
        System.out.println("注册");

    }



}
