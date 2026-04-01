package com.autismhealth.modules.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 健康记录实体。
 */
@Data
@TableName("health_record")
public class HealthRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long childId;
    private LocalDate recordDate;
    private Double temperature;
    private Integer heartRate;
    private Double sleepHours;
    private String appetiteLevel;
    private String emotionState;
    private String bowelStatus;
    private String allergyReaction;
    private String abnormalSymptom;
    private String careAdvice;
    private Long recordedBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
