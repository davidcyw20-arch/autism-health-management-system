package com.autismhealth.modules.user.entity;

import lombok.Data;

/**
 * 用户实体。
 */
@Data
public class SysUser {
    private Long id;
    private String username;
    private String realName;
    private String roleCode;
    private String phone;
    private Integer status;
}
