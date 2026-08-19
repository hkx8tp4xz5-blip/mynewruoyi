package com.example.myruoyi.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(AllExceptionHandle.class); // 记录日志

    @ExceptionHandler(RuntimeException.class)               // 处理运行时异常
    public Result RuntimeException(RuntimeException e) {
        logger.warn("业务异常：{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)                      // 处理其他异常
    public Result Exception(Exception e) {
        logger.error("系统异常", e);
        return Result.fail("系统异常");
    }

}
