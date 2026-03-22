package com.autismhealth.modules.rehabilitation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 康复训练新增/修改参数。
 */
@Data
public class RehabilitationSaveDTO {
    @NotNull(message = "儿童ID不能为空")
    private Long childId;
    private Long trainerId;
    @NotNull(message = "训练日期不能为空")
    private LocalDate trainingDate;
    @NotBlank(message = "训练类型不能为空")
    private String trainingType;
    private String trainingTheme;
    @NotNull(message = "训练时长不能为空")
    private Integer durationMinutes;
    private String participationLevel;
    private String performanceSummary;
    private String trainingGoal;
    private String nextPlan;
}
