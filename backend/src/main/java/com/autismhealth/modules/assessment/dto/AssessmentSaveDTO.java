package com.autismhealth.modules.assessment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 评估记录新增/修改参数。
 */
@Data
public class AssessmentSaveDTO {
    @NotNull(message = "儿童ID不能为空")
    private Long childId;
    @NotNull(message = "评估日期不能为空")
    private LocalDate assessmentDate;
    @NotBlank(message = "评估类型不能为空")
    private String assessmentType;
    private Long evaluatorId;
    private Double languageScore;
    private Double socialScore;
    private Double behaviorScore;
    private Double cognitionScore;
    private Double sensoryScore;
    private Double totalScore;
    private String assessmentConclusion;
    private String interventionSuggestion;
}
