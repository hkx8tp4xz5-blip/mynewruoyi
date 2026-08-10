package com.example.myruoyi.controller;

import com.example.myruoyi.common.Result;
import com.example.myruoyi.domain.LoginBody;
import com.example.myruoyi.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {       //登录方法
    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")          //接收login
    public Result login(@RequestBody LoginBody loginBody) {     // 前端json变Java对象
        try {
            String token = sysLoginService.login(loginBody.getUserName(), loginBody.getPassword()); //把json变成对象
            return Result.success(token);
        } catch (RuntimeException e) {
            return Result.fail(e.getMessage());
        }
    }


}



