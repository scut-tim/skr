package nulll.skr.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class AllInterceptor implements HandlerInterceptor {



    @Autowired
    private SessionManagement sessionManagement;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,Object handler)throws Exception{


        System.out.println("被拦截的:");

        System.out.println("getContextPath:" + request.getContextPath());
        System.out.println("getServletPath:" + request.getServletPath());
        System.out.println("getRequestURI:" + request.getRequestURI());
        System.out.println("getRequestURL:" + request.getRequestURL());
        System.out.println("requestMthod:"+request.getMethod());




        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){

            if(cookie.getName().equals("userId")){

                int userId = Integer.valueOf(cookie.getValue());
                if(sessionManagement.getUser(userId,request)){
                    System.out.println("已登录");
                    return true;

                }else{
                    System.out.println("没登陆，就当无事发生");
                    response.sendError(404);
                    return false;
                }

            }

        }
        System.out.println("没登陆!");
        return false;

    }

}
