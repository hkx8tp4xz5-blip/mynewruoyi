package com.example.myruoyi.mapper;

import com.example.myruoyi.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    public SysUser selectByUsername(String userName);
}
