package com.autismhealth.modules.followup.dto;

import lombok.Data;

/**
 * 随访记录查询参数。
 */
@Data
public class FollowUpQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private Long parentUserId;
    private Long childId;
    private String followUpMethod;
    private String followUpDateStart;
    private String followUpDateEnd;
}
