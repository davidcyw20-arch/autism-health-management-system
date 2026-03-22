package com.autismhealth.modules.diagnosis.entity;

import lombok.Data;

/**
 * 诊断信息实体。
 */
@Data
public class DiagnosisInfo {
    private Long id;
    private Long childId;
    private String diagnosisDate;
    private String diagnosisHospital;
    private String diagnosisDoctor;
    private String diagnosisResult;
    private String autismLevel;
}
