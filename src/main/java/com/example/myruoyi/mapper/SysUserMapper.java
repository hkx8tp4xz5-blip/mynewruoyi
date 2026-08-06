package com.example.myruoyi.mapper;

import com.example.myruoyi.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    SysUser selectByUsername(String userName);
}
