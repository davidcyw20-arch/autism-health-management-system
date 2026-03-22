package com.autismhealth.modules.health.entity;

import lombok.Data;

/**
 * 健康记录实体。
 */
@Data
public class HealthRecord {
    private Long id;
    private Long childId;
    private String recordDate;
    private Double temperature;
    private Integer heartRate;
    private Double sleepHours;
    private String emotionState;
    private String abnormalSymptom;
}
