package com.example.myruoyi.service;

import com.example.myruoyi.common.TokenService;
import com.example.myruoyi.domain.SysUser;
import com.example.myruoyi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    public String login(String username, String password) {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (username == null || password == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!sysUser.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        return tokenService.createToken(sysUser);
    }

}



