package com.example.myruoyi.domain;

import lombok.Data;

@Data
public class SysUser {
    private Long userId;
    private String userName;
    private String password;
    private String status;
    private String delFlag;
    private String createTime;
}
