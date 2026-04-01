package com.autismhealth.modules.parent.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 家长信息新增/修改参数。
 */
@Data
public class ParentSaveDTO {
    private Long userId;
    @NotBlank(message = "家长姓名不能为空")
    private String parentName;
    private Integer gender;
    @NotBlank(message = "关系类型不能为空")
    private String relationType;
    @NotBlank(message = "联系电话不能为空")
    private String phone;
    private String wechat;
    private String occupation;
    private String educationLevel;
    private String address;
    private String emergencyContact;
    private String emergencyPhone;
    private String remark;
}
