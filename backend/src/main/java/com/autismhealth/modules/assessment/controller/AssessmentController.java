package com.autismhealth.modules.assessment.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.common.support.DateQueryHelper;
import com.autismhealth.common.support.EntityReferenceValidator;
import com.autismhealth.modules.assessment.dto.AssessmentQueryDTO;
import com.autismhealth.modules.assessment.dto.AssessmentSaveDTO;
import com.autismhealth.modules.assessment.entity.AssessmentRecord;
import com.autismhealth.modules.assessment.service.AssessmentRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 评估记录控制器。
 */
@RestController
@RequestMapping("/assessments")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentRecordService assessmentRecordService;
    private final EntityReferenceValidator entityReferenceValidator;

    @GetMapping
    public Result<Page<AssessmentRecord>> page(AssessmentQueryDTO queryDTO) {
        LocalDate assessmentDateStart = DateQueryHelper.parseNullableDate(queryDTO.getAssessmentDateStart());
        LocalDate assessmentDateEnd = DateQueryHelper.parseNullableDate(queryDTO.getAssessmentDateEnd());
        LambdaQueryWrapper<AssessmentRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryDTO.getChildId() != null, AssessmentRecord::getChildId, queryDTO.getChildId())
                .eq(StringUtils.hasText(queryDTO.getAssessmentType()), AssessmentRecord::getAssessmentType, queryDTO.getAssessmentType())
                .ge(assessmentDateStart != null, AssessmentRecord::getAssessmentDate, assessmentDateStart)
                .le(assessmentDateEnd != null, AssessmentRecord::getAssessmentDate, assessmentDateEnd)
                .orderByDesc(AssessmentRecord::getAssessmentDate);
        return Result.success(assessmentRecordService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper));
    }

    @GetMapping("/{id}")
    public Result<AssessmentRecord> detail(@PathVariable Long id) {
        return Result.success(assessmentRecordService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody AssessmentSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getEvaluatorId(), "评估人");
        AssessmentRecord entity = new AssessmentRecord();
        BeanUtils.copyProperties(dto, entity);
        return Result.success("新增成功", assessmentRecordService.save(entity));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody AssessmentSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getEvaluatorId(), "评估人");
        AssessmentRecord entity = new AssessmentRecord();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);
        return Result.success("修改成功", assessmentRecordService.updateById(entity));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", assessmentRecordService.removeById(id));
    }
}
