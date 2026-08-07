package com.example.myruoyi.service;

import com.example.myruoyi.common.TokenService;
import com.example.myruoyi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TokenService tokenService;
}



