package com.autismhealth.modules.user.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.user.entity.SysUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器。
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public Result<PageResult<SysUser>> page() {
        SysUser one = new SysUser();
        one.setId(1L);
        one.setUsername("admin");
        one.setRealName("系统管理员");
        one.setRoleCode("admin");
        one.setPhone("13800000001");
        one.setStatus(1);
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<SysUser> detail(@PathVariable Long id) {
        SysUser one = new SysUser();
        one.setId(id);
        one.setUsername("admin");
        one.setRealName("系统管理员");
        one.setRoleCode("admin");
        one.setPhone("13800000001");
        one.setStatus(1);
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody SysUser user) {
        return Result.success("新增成功", "user created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody SysUser user) {
        return Result.success("修改成功", "user updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "user deleted: " + id);
    }
}
