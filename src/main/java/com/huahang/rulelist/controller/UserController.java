package com.huahang.rulelist.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制类 作为测试
 * @Author: 阿俊哥
 * @Date: 2019/9/3 15:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户管理",tags = {"用户管理"})
public class UserController {

    @GetMapping
    @ApiOperation("获取列表")
    @RequiresPermissions("user:list")
    public void list(){
        System.out.println();
    }

    @GetMapping("/{userId}")
    @ApiOperation("获取详情")
    @RequiresPermissions("user:get")
    public void getUserById(@PathVariable String userId){
        System.out.println();
    }


    @PostMapping
    @ApiOperation("保存用户")
    @RequiresPermissions("user:save")
    public void save(){
        System.out.println();
    }


    @PutMapping
    @ApiOperation("修改用户")
    @RequiresPermissions("user:update")
    public void editSave(){
        System.out.println();
    }
}
