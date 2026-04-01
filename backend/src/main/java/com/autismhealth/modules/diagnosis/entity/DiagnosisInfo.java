package com.autismhealth.modules.diagnosis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 诊断信息实体。
 */
@Data
@TableName("diagnosis_info")
public class DiagnosisInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long childId;
    private LocalDate diagnosisDate;
    private String diagnosisHospital;
    private String diagnosisDoctor;
    private String diagnosisResult;
    private String autismLevel;
    private String comorbidity;
    private String suggestion;
    private String attachmentUrl;
    private Long createBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
