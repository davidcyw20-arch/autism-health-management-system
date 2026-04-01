package com.autismhealth.modules.user.controller;

import com.autismhealth.common.exception.BusinessException;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.user.dto.UserQueryDTO;
import com.autismhealth.modules.user.dto.UserSaveDTO;
import com.autismhealth.modules.user.entity.SysUser;
import com.autismhealth.modules.user.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * 用户管理控制器。
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final Set<String> SUPPORTED_ROLE_CODES = Set.of("admin", "doctor", "parent");

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
    public Result<Boolean> add(@Valid @RequestBody UserSaveDTO dto) {
        validateRoleCode(dto.getRoleCode());
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        if (!StringUtils.hasText(user.getPassword())) {
            user.setPassword("123456");
        }
        return Result.success("新增成功", sysUserService.save(user));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody UserSaveDTO dto) {
        validateRoleCode(dto.getRoleCode());
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        user.setId(id);
        if (!StringUtils.hasText(dto.getPassword())) {
            SysUser existingUser = sysUserService.getById(id);
            if (existingUser == null) {
                throw new BusinessException("用户不存在");
            }
            user.setPassword(existingUser.getPassword());
        }
        return Result.success("修改成功", sysUserService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", sysUserService.removeById(id));
    }

    private void validateRoleCode(String roleCode) {
        if (!SUPPORTED_ROLE_CODES.contains(roleCode)) {
            throw new BusinessException("角色编码不支持，请使用 admin、doctor 或 parent");
        }
    }
}
