package com.autismhealth.modules.rehabilitation.entity;

import lombok.Data;

/**
 * 康复训练记录实体。
 */
@Data
public class RehabilitationRecord {
    private Long id;
    private Long childId;
    private String trainingDate;
    private String trainingType;
    private String trainingTheme;
    private Integer durationMinutes;
    private String performanceSummary;
}
