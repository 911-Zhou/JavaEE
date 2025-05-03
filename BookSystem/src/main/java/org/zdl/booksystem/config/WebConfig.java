package org.zdl.booksystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zdl.booksystem.interceptor.LoginInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    private List<String> excludePath = List.of(
            "/user/login",
            "/**/*.js",
            "/**/*.css",
            "/**/*.png",
            "/**/*.html");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册⾃定义拦截器对象
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")//设置拦截器拦截的请求路径（ /** 表⽰拦截所有请求)
                .excludePathPatterns(excludePath);
    }
}
