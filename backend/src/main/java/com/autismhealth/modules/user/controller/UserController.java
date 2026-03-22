package com.autismhealth.modules.user.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.modules.user.dto.UserQueryDTO;
import com.autismhealth.modules.user.entity.SysUser;
import com.autismhealth.modules.user.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器。
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final SysUserService sysUserService;

    @GetMapping
    public Result<Page<SysUser>> page(UserQueryDTO queryDTO) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(queryDTO.getUsername()), SysUser::getUsername, queryDTO.getUsername())
                .like(StringUtils.hasText(queryDTO.getRealName()), SysUser::getRealName, queryDTO.getRealName())
                .eq(StringUtils.hasText(queryDTO.getRoleCode()), SysUser::getRoleCode, queryDTO.getRoleCode())
                .eq(queryDTO.getStatus() != null, SysUser::getStatus, queryDTO.getStatus())
                .orderByDesc(SysUser::getCreateTime);
        Page<SysUser> page = sysUserService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<SysUser> detail(@PathVariable Long id) {
        return Result.success(sysUserService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@RequestBody SysUser user) {
        return Result.success("新增成功", sysUserService.save(user));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody SysUser user) {
        user.setId(id);
        return Result.success("修改成功", sysUserService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", sysUserService.removeById(id));
    }
}
