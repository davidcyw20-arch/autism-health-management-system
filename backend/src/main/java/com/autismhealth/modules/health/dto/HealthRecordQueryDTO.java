package com.autismhealth.modules.health.dto;

import lombok.Data;

/**
 * 健康记录查询参数。
 */
@Data
public class HealthRecordQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private Long parentUserId;
    private Long childId;
    private String emotionState;
    private String recordDateStart;
    private String recordDateEnd;
}
