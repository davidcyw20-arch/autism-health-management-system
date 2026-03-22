package com.autismhealth.modules.rehabilitation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 康复训练记录实体。
 */
@Data
@TableName("rehabilitation_record")
public class RehabilitationRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long childId;
    private Long trainerId;
    private LocalDate trainingDate;
    private String trainingType;
    private String trainingTheme;
    private Integer durationMinutes;
    private String participationLevel;
    private String performanceSummary;
    private String trainingGoal;
    private String nextPlan;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
