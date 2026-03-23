package com.autismhealth.modules.rehabilitation.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.common.support.EntityReferenceValidator;
import com.autismhealth.modules.rehabilitation.dto.RehabilitationQueryDTO;
import com.autismhealth.modules.rehabilitation.dto.RehabilitationSaveDTO;
import com.autismhealth.modules.rehabilitation.entity.RehabilitationRecord;
import com.autismhealth.modules.rehabilitation.service.RehabilitationRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 康复训练控制器。
 */
@RestController
@RequestMapping("/rehabilitation")
@RequiredArgsConstructor
public class RehabilitationController {

    private final RehabilitationRecordService rehabilitationRecordService;
    private final EntityReferenceValidator entityReferenceValidator;

    @GetMapping
    public Result<Page<RehabilitationRecord>> page(RehabilitationQueryDTO queryDTO) {
        LambdaQueryWrapper<RehabilitationRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryDTO.getChildId() != null, RehabilitationRecord::getChildId, queryDTO.getChildId())
                .eq(StringUtils.hasText(queryDTO.getTrainingType()), RehabilitationRecord::getTrainingType, queryDTO.getTrainingType())
                .ge(StringUtils.hasText(queryDTO.getTrainingDateStart()), RehabilitationRecord::getTrainingDate, LocalDate.parse(queryDTO.getTrainingDateStart()))
                .le(StringUtils.hasText(queryDTO.getTrainingDateEnd()), RehabilitationRecord::getTrainingDate, LocalDate.parse(queryDTO.getTrainingDateEnd()))
                .orderByDesc(RehabilitationRecord::getTrainingDate);
        return Result.success(rehabilitationRecordService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper));
    }

    @GetMapping("/{id}")
    public Result<RehabilitationRecord> detail(@PathVariable Long id) {
        return Result.success(rehabilitationRecordService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody RehabilitationSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getTrainerId(), "康复师");
        RehabilitationRecord entity = new RehabilitationRecord();
        BeanUtils.copyProperties(dto, entity);
        return Result.success("新增成功", rehabilitationRecordService.save(entity));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody RehabilitationSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getTrainerId(), "康复师");
        RehabilitationRecord entity = new RehabilitationRecord();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);
        return Result.success("修改成功", rehabilitationRecordService.updateById(entity));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", rehabilitationRecordService.removeById(id));
    }
}
