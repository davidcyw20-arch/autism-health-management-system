package com.autismhealth.modules.rehabilitation.dto;

import lombok.Data;

/**
 * 康复训练查询参数。
 */
@Data
public class RehabilitationQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private Long parentUserId;
    private Long childId;
    private String trainingType;
    private String trainingDateStart;
    private String trainingDateEnd;
}
