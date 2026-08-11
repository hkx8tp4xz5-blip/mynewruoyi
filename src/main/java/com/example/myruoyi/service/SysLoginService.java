package com.example.myruoyi.service;

import com.example.myruoyi.common.TokenFilter;
import com.example.myruoyi.common.TokenService;
import com.example.myruoyi.domain.SysUser;
import com.example.myruoyi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {         //核验登陆者的身份和信息，核对账号密码，正确则发token

    @Autowired
    private SysUserMapper sysUserMapper;        // 自动装配的用户Mapper

    @Autowired
    private TokenService tokenService;          // 自动装配Token
    @Autowired
    private TokenFilter tokenFilter;

    public String login(String username, String password) {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!sysUser.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        return tokenService.createToken(sysUser);
    }

    public void logout(String token) {
        tokenService.deleteToken(token);
    }
    }



