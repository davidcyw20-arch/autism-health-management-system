package com.autismhealth.modules.user.dto;

import lombok.Data;

/**
 * 用户查询参数。
 */
@Data
public class UserQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private String username;
    private String realName;
    private String roleCode;
    private Integer status;
}
