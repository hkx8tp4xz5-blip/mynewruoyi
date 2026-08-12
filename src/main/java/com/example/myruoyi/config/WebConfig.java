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
                .excludePathPatterns("/login","/login.html");         // 除了登录
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

//    @Bean
//    public CorsFilter corsFilter()
//    {
//        CorsConfiguration config = new CorsConfiguration();
//        // 设置访问源地址
//        config.addAllowedOriginPattern("*");
//        // 设置访问源请求头
//        config.addAllowedHeader("*");
//        // 设置访问源请求方法
//        config.addAllowedMethod("*");
//        // 有效期 1800秒
//        config.setMaxAge(1800L);
//        // 添加映射路径，拦截一切请求
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        // 返回新的CorsFilter
//        return new CorsFilter(source);
//    }


}
