package com.example.myruoyi.controller;

import com.example.myruoyi.common.Result;
import com.example.myruoyi.domain.LoginBody;
import com.example.myruoyi.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {       //登录方法
    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")          //接收login
    public Result login(@RequestBody LoginBody loginBody) { // 前端json转Java对象
        try {
            String token = sysLoginService.login(      //  进service层
                    loginBody.getUserName(),           //  前端数据
                    loginBody.getPassword(),           //  前端数据
                    loginBody.getCode(),               //  前端数据
                    loginBody.getUuid());              //  前端数据
            return Result.success(token);
        } catch (RuntimeException e) {
            return Result.fail(e.getMessage());
        }
    }


    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        sysLoginService.logout(token);
        return Result.success("退出成功");
    }

    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader("Authorization") String token) {
        String username = sysLoginService.getCurrentUser(token);
        if (username == null) {
            return Result.fail("用户未登录");
        }
        return Result.success(username);


    }
}



