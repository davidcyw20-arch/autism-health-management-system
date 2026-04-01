package com.autismhealth.modules.assessment.dto;

import lombok.Data;

/**
 * 评估记录查询参数。
 */
@Data
public class AssessmentQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private Long parentUserId;
    private Long childId;
    private String assessmentType;
    private String assessmentDateStart;
    private String assessmentDateEnd;
}
