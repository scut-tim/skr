package nulll.skr.Interceptor;


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Resource
    private AllInterceptor allInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){


        registry.addInterceptor(allInterceptor).excludePathPatterns("/static/**")
               .excludePathPatterns("/*.html","/*.js","/userLogin","/");
//        registry.addInterceptor(allInterceptor);

    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){
//
//
//        resourceHandlerRegistry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//
//    }



}
