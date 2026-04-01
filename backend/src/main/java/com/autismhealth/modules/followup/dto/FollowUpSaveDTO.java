package com.autismhealth.modules.followup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 随访记录新增/修改参数。
 */
@Data
public class FollowUpSaveDTO {
    @NotNull(message = "儿童ID不能为空")
    private Long childId;
    @NotNull(message = "随访日期不能为空")
    private LocalDate followUpDate;
    @NotBlank(message = "随访方式不能为空")
    private String followUpMethod;
    private Long followUpPerson;
    private String recoveryProgress;
    private String medicationStatus;
    private String familyFeedback;
    private String problemFound;
    private String handlingAdvice;
    private LocalDate nextFollowUpDate;
}
