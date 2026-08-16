package com.example.myruoyi.mapper;

import com.example.myruoyi.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {     //方法层，只管方法，按用户的要求去数据库查询
    SysUser selectByUsername(String userName);
    List<SysUser> selectUserList(SysUser user);
    SysUser selectByUserId(Long userId);
    int insertUser(SysUser user);
    int updateUser(SysUser user);
    int deleteUser(Long userId);
}
