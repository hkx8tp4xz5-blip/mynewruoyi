package com.example.myruoyi.common;


import com.example.myruoyi.domain.SysUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Value("${token.secret}")
    private String secret;                                 // 设置密钥

    @Value("${token.expireTime}")
    private Long expireTime;                              // 设置过期时间

    private StringRedisTemplate redisTemplate;            // 设置Redis模板

    public TokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes());        //生成密钥
    }

    public String createToken(SysUser user) {                // token信息
        String uuid = UUID.randomUUID().toString();
        String token = Jwts.builder()
                .setSubject(user.getUserName())        
                .claim("userId", user.getUserId())
                .claim("uuid", uuid)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(key())
                .compact();
        redisTemplate.opsForValue().set(        //redis登记信息
                "token" + uuid,
                token,
                expireTime,
                TimeUnit.MILLISECONDS
        );

        return token;
    }


}




