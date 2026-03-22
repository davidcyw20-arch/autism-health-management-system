package com.autismhealth.modules.assessment.entity;

import lombok.Data;

/**
 * 评估记录实体。
 */
@Data
public class AssessmentRecord {
    private Long id;
    private Long childId;
    private String assessmentDate;
    private String assessmentType;
    private Double totalScore;
    private String assessmentConclusion;
}
