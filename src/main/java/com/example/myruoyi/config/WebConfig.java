package com.example.myruoyi.config;

import com.example.myruoyi.common.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;


    public WebConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {      // 写入拦截器
        registry.addInterceptor(jwtInterceptor)            // 写入拦截器
                .addPathPatterns("/**")                 // 拦截所有请求    
                .excludePathPatterns("/login", "/login.html", "/home.html", "/captchaImage","/user.html");         // 除了登录
    }

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")                  // 地址来源
                .allowedHeaders("*")                  // 请求头
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");  // 增删改查
    }


}
