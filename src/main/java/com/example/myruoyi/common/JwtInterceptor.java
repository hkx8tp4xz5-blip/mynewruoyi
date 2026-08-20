package com.example.myruoyi.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {          // token验证拦截器
    private final TokenService tokenService;

    public JwtInterceptor(TokenService tokenService) {       // 连接token验证的工具
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authorization");        // 取出Authorization,存进token
        String username = tokenService.parseToken(token);
        if (username == null) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"msg\":\"登录已过期，请重新登录\"}");  // 统一返回JSON，方便前端识别
            return false;
        }
        return true;
    }
}

