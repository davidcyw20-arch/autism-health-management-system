package com.autismhealth.modules.parent.dto;

import lombok.Data;

/**
 * 家长信息查询参数。
 */
@Data
public class ParentQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private String parentName;
    private String phone;
    private String relationType;
}
