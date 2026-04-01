package com.autismhealth.modules.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 评估记录实体。
 */
@Data
@TableName("assessment_record")
public class AssessmentRecord {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long childId;
    private LocalDate assessmentDate;
    private String assessmentType;
    private Long evaluatorId;
    private Double languageScore;
    private Double socialScore;
    private Double behaviorScore;
    private Double cognitionScore;
    private Double sensoryScore;
    private Double totalScore;
    private String assessmentConclusion;
    private String interventionSuggestion;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
