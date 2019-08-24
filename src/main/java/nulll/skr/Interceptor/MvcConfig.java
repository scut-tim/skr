package nulll.skr.Interceptor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${skr.imagePath}")
    private String imagePath;



    @Resource
    private AllInterceptor allInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){


        registry.addInterceptor(allInterceptor).excludePathPatterns("/static/**")
               .excludePathPatterns("/*.html","/*.js","/userLogin","/","/user");
//        registry.addInterceptor(allInterceptor);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){


        resourceHandlerRegistry.addResourceHandler("/image/**")
                .addResourceLocations("file:"+imagePath);

    }



}
