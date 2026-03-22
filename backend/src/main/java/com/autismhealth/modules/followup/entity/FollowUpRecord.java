package com.autismhealth.modules.followup.entity;

import lombok.Data;

/**
 * 随访记录实体。
 */
@Data
public class FollowUpRecord {
    private Long id;
    private Long childId;
    private String followUpDate;
    private String followUpMethod;
    private String recoveryProgress;
    private String familyFeedback;
}
