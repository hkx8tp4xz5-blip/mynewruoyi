package com.example.myruoyi.controller;

import com.example.myruoyi.common.Result;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {                        // 验证码操作
    @Autowired
    private StringRedisTemplate stringRedisTemplate;    // 自动存入redis

    @Autowired
    private Producer producer;

    @GetMapping("/captchaImage")
    public Result getCode() {
        String uuid = "captcha:" + UUID.randomUUID().toString().replace("-", ""); // 生成uuid
        String code = producer.createText();                // 生成验证码code，四个字符   producer
        BufferedImage image = producer.createImage(code);   // 把验证码画成图片

        // set(key, 值, 时间, 单位) 存储redis验证码和过期时间
        stringRedisTemplate.opsForValue().set(uuid, code, 5, TimeUnit.MINUTES);  // 存储redis验证码

        ByteArrayOutputStream os = new ByteArrayOutputStream();             // new一个字节的暂存区os
        try {
            ImageIO.write(image, "jpg", os);                    // 把图片转为jpg字节
        } catch (IOException e) {
            throw new RuntimeException(e);                                 // 文件读写类异常
        }
        String img = Base64.getEncoder().encodeToString(os.toByteArray()); // 把字节转为base64字符串

        Map<String, String> map = new HashMap<>();
        map.put("uuid", uuid);                              // 存下uuid
        map.put("img", img);                                // 存下图片
        return Result.success(map);                         // 返回结果，把result变成json字符串
    }


}
