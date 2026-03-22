package com.autismhealth.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 用户新增/修改参数。
 */
@Data
public class UserSaveDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @NotBlank(message = "角色编码不能为空")
    private String roleCode;

    @NotNull(message = "账号状态不能为空")
    private Integer status;

    private String phone;
    private String email;
    private Integer gender;
    private String password;
    private String remark;
}
