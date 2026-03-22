package com.autismhealth.modules.diagnosis.dto;

import lombok.Data;

/**
 * 诊断信息查询参数。
 */
@Data
public class DiagnosisQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private Long childId;
    private String diagnosisHospital;
    private String autismLevel;
}
