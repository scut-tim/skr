package nulll.skr.Interceptor;


import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionManagement {

    public boolean setUser(int userId, HttpServletRequest request, HttpServletResponse response){




        List<Integer> userLoginList;

        userLoginList = (List)request.getSession().getAttribute("userLoginList");
        if(userLoginList == null){
            userLoginList = new ArrayList<>();
            System.out.println("向session添加属性");
            request.getSession().setAttribute("userLoginList",userLoginList);
        }

        userLoginList.add(userId);

        return true;
    }


    public boolean getUser(int userId,HttpServletRequest request){


        List<Integer> userLoginList = (List)request.getSession().getAttribute("userLoginList");

        if(userLoginList == null) return false;
        else if(userLoginList.contains(userId))return true;

        System.out.println("假的吧");
        return false;

    }


}
