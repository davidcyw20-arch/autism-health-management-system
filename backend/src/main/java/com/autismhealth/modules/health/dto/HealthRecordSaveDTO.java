package com.autismhealth.modules.health.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 健康记录新增/修改参数。
 */
@Data
public class HealthRecordSaveDTO {
    @NotNull(message = "儿童ID不能为空")
    private Long childId;
    @NotNull(message = "记录日期不能为空")
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
}
