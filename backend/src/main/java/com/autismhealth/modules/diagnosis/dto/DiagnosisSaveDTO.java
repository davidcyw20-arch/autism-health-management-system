package com.autismhealth.modules.diagnosis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * 诊断信息新增/修改参数。
 */
@Data
public class DiagnosisSaveDTO {
    @NotNull(message = "儿童ID不能为空")
    private Long childId;
    @NotNull(message = "诊断日期不能为空")
    private LocalDate diagnosisDate;
    @NotBlank(message = "诊断机构不能为空")
    private String diagnosisHospital;
    private String diagnosisDoctor;
    @NotBlank(message = "诊断结果不能为空")
    private String diagnosisResult;
    @NotBlank(message = "孤独症等级不能为空")
    private String autismLevel;
    private String comorbidity;
    private String suggestion;
    private String attachmentUrl;
    private Long createBy;
}
