package com.autismhealth.modules.auth.controller;

import com.autismhealth.common.exception.BusinessException;
import com.autismhealth.common.result.Result;
import com.autismhealth.common.security.JwtTokenUtil;
import com.autismhealth.modules.auth.dto.LoginDTO;
import com.autismhealth.modules.auth.dto.RegisterDTO;
import com.autismhealth.modules.auth.vo.LoginVO;
import com.autismhealth.modules.user.entity.SysUser;
import com.autismhealth.modules.user.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录认证控制器。
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO dto) {
        SysUser user = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, dto.getUsername())
                .eq(SysUser::getPassword, dto.getPassword())
                .last("limit 1"));
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        String token = JwtTokenUtil.generateToken(user.getUsername(), Map.of("roleCode", user.getRoleCode(), "userId", user.getId()));
        LoginVO vo = new LoginVO(token, user.getId(), user.getUsername(), user.getRealName(), user.getRoleCode());
        return Result.success("登录成功", vo);
    }

    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterDTO dto) {
        long count = sysUserService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, dto.getUsername()));
        if (count > 0) {
            throw new BusinessException("用户名已存在");
        }
        SysUser user = new SysUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRealName(dto.getRealName());
        user.setPhone(dto.getPhone());
        user.setRoleCode("parent");
        user.setStatus(1);
        sysUserService.save(user);
        return Result.success("注册成功", "registered");
    }
}
