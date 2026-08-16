package com.example.myruoyi.service;

import com.example.myruoyi.domain.SysUser;
import com.example.myruoyi.mapper.SysUserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PageInfo<SysUser> selectUserList(SysUser user, int pageNum, int pageSize) { // 分页查询用户列表
        PageHelper.startPage(pageNum, pageSize);    // 先分页
        List<SysUser> list = sysUserMapper.selectUserList(user);    // 查数据库
        return new PageInfo<>(list);    // 返回分页结果
    }

    public SysUser selectByUserId(Long userId) {
        return sysUserMapper.selectByUserId(userId);
    }

    public int insertUser(SysUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return sysUserMapper.insertUser(user);
    }

    public int updateUser(SysUser user){
        return sysUserMapper.updateUser(user);
    }

    public int deleteUser(Long userId){
        return sysUserMapper.deleteUser(userId);
    }
}