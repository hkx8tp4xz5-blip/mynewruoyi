package com.example.myruoyi.service;

import com.example.myruoyi.common.TokenService;
import com.example.myruoyi.domain.SysUser;
import com.example.myruoyi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysLoginService {         //核验登陆者的身份和信息，核对账号密码，正确则发token

    @Autowired
    private SysUserMapper sysUserMapper;        // 自动装配的用户Mapper

    @Autowired
    private TokenService tokenService;          // 自动装配Token

    @Autowired
    private PasswordEncoder passwordEncoder;    // 自动装配密码编码器
    @Autowired
    private StringRedisTemplate stringRedisTemplate;       // 自动装配Redis

    public String login(String username, String password, String code, String uuid) {
        SysUser sysUser = sysUserMapper.selectByUsername(username);

        String redisCode = stringRedisTemplate.opsForValue().get(uuid);
        if (redisCode == null || !redisCode.equals(code)) {          // 先查验证码
            throw new RuntimeException("验证码错误");
        }

        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!passwordEncoder.matches(password, sysUser.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        return tokenService.createToken(sysUser);    // 跳进造tokenservice，造完返回token
    }

    public void logout(String token) {
        tokenService.deleteToken(token);                  // 删除token
    }
    public String getCurrentUser(String token) {
        return tokenService.getUserName(token);          // 返回给当前用户
    }
}



