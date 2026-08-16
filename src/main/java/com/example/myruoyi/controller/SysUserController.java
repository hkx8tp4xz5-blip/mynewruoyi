package com.example.myruoyi.controller;

import com.example.myruoyi.common.Result;
import com.example.myruoyi.domain.SysUser;
import com.example.myruoyi.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")    // 查列表所有
    public Result list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(required = false) String userName,
                       @RequestParam(required = false) String status) {
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setStatus(status);

        PageInfo<SysUser> pageInfo = sysUserService.selectUserList(user, pageNum, 10);// 调Service分页查

        Map<String, Object> map = new HashMap<>();
        map.put("rows", pageInfo.getList());           // rows = 当前页的用户列表
        map.put("total", pageInfo.getTotal());         // total = 总条数
        return Result.success(map);                   // 
    }

    @GetMapping("/{userId}")        // 根据id查
    public Result get(@PathVariable Long userId) {
        SysUser user = sysUserService.selectByUserId(userId);
        return Result.success(user);
    }

    @PostMapping    // 添加用户
    public Result add(@RequestBody SysUser user) {
        sysUserService.insertUser(user);
        return Result.success(user);
    }

    @PutMapping    // 修改用户
    public Result update(@RequestBody SysUser user) {
        sysUserService.updateUser(user);
        return Result.success(user);
    }

    @DeleteMapping("/{userId}")    // 删除用户
    public Result delete(@PathVariable Long userId) {
        sysUserService.deleteUser(userId);
        return Result.success(userId);
    }
    
}
