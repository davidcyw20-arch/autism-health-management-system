package com.autismhealth.modules.child.entity;

import lombok.Data;

/**
 * 儿童档案实体。
 */
@Data
public class ChildProfile {
    private Long id;
    private String childNo;
    private String childName;
    private Integer gender;
    private String birthDate;
    private String autismLevel;
    private String schoolName;
    private String address;
    private Integer status;
}
