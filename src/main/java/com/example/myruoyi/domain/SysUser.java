package com.example.myruoyi.domain;
import lombok.Data;

@Data
public class SysUser {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String password;
    private String status;
    private String delFlag;
    private String createTime;

}
