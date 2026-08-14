package com.example.myruoyi.common;


import com.example.myruoyi.domain.SysUser;
import io.jsonwebtoken.Claims;
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
public class TokenService {                                //生成token的方法
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

    /**
     * 生成token
     */

    public String createToken(SysUser user) {                // 造token信息
        String uuid = UUID.randomUUID().toString();          // 生成随机编号 uuid
        String token = Jwts.builder()
                .setSubject(user.getUserName())               // 写入用户名
                .claim("userId", user.getUserId())      // 写入用户ID
                .claim("uuid", uuid)                    // 写入UUID
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))  // 设置过期时间
                .signWith(key())                              // 设置签名
                .compact();
        redisTemplate.opsForValue().set(        //redis信息
                "token:" + uuid,
                token,
                expireTime,
                TimeUnit.MILLISECONDS
        );

        return token;
    }

    /**
     * 解析token
     */

    public String parseToken(String token) {          // 验证token信息
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String uuid = claims.get("uuid").toString();
            String redisToken = redisTemplate.opsForValue().get("token:" + uuid);
            if (redisToken == null || !redisToken.equals(token)) {
                return null;
            }
            return claims.getSubject();

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 删除token
     */
    public String deleteToken(String token) {
        String username = parseToken(token);
        if (username == null){
            return "退出失败，token已过期";
        }
        String uuid = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("uuid")
                .toString();
        redisTemplate.delete("token:" + uuid);
        return "退出成功";
    }


}




