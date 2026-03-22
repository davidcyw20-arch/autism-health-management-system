package com.autismhealth.modules.auth.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.common.security.JwtTokenUtil;
import com.autismhealth.modules.auth.dto.LoginDTO;
import com.autismhealth.modules.auth.vo.LoginVO;
import jakarta.validation.Valid;
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
public class AuthController {

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginDTO dto) {
        String roleCode = switch (dto.getUsername()) {
            case "admin" -> "admin";
            case "doctor01", "therapist01" -> "doctor";
            default -> "parent";
        };
        String token = JwtTokenUtil.generateToken(dto.getUsername(), Map.of("roleCode", roleCode));
        LoginVO vo = new LoginVO(token, dto.getUsername(), dto.getUsername(), roleCode);
        return Result.success("登录成功", vo);
    }
}
