package com.autismhealth.modules.parent.entity;

import lombok.Data;

/**
 * 家长信息实体。
 */
@Data
public class ParentInfo {
    private Long id;
    private String parentName;
    private String relationType;
    private String phone;
    private String occupation;
    private String address;
}
