package com.autismhealth.modules.child.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 儿童档案实体。
 */
@Data
@TableName("child_profile")
public class ChildProfile {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String childNo;
    private String childName;
    private Integer gender;
    private LocalDate birthDate;
    private String idCard;
    private String bloodType;
    private Double height;
    private Double weight;
    private String allergyHistory;
    private String familyMedicalHistory;
    private String autismLevel;
    private String schoolName;
    private String address;
    private LocalDate admissionDate;
    private Integer status;
    private String remark;
    @TableLogic
    private Integer deleted;
    private Long createBy;
    private Long updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
