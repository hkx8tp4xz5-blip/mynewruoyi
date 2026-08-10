package com.example.myruoyi.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenFilter implements HandlerInterceptor {
    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {       // 连接token验证的工具
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");

        try {
            tokenService.parseToken(token);
            return true;
        } catch (RuntimeException e) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            return false;
        }
    }
    
}
