package com.autismhealth.modules.child.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 儿童档案新增/修改参数。
 */
@Data
public class ChildSaveDTO {
    @NotBlank(message = "档案编号不能为空")
    private String childNo;

    @NotBlank(message = "儿童姓名不能为空")
    private String childName;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotNull(message = "出生日期不能为空")
    private LocalDate birthDate;

    @NotBlank(message = "孤独症程度不能为空")
    private String autismLevel;

    private String schoolName;
    private String address;
    private Integer status;
    private String remark;
}
