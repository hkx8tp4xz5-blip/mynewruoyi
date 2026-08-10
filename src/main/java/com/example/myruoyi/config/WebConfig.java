package com.example.myruoyi.config;

import com.example.myruoyi.common.TokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private TokenFilter tokenFilter;


    public WebConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }



}
