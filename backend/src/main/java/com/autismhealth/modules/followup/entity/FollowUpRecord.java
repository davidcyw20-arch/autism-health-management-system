package com.autismhealth.modules.followup.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 随访记录实体。
 */
@Data
@TableName("follow_up_record")
public class FollowUpRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long childId;
    private LocalDate followUpDate;
    private String followUpMethod;
    private Long followUpPerson;
    private String recoveryProgress;
    private String medicationStatus;
    private String familyFeedback;
    private String problemFound;
    private String handlingAdvice;
    private LocalDate nextFollowUpDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
