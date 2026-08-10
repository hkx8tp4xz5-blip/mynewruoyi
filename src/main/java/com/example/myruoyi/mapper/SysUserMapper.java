package com.example.myruoyi.mapper;

import com.example.myruoyi.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {     //方法层，只管方法，按用户的要求去数据库查询
    public SysUser selectByUsername(String userName);
}
