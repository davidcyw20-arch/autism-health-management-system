package com.autismhealth.modules.health.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.common.support.EntityReferenceValidator;
import com.autismhealth.modules.health.dto.HealthRecordQueryDTO;
import com.autismhealth.modules.health.dto.HealthRecordSaveDTO;
import com.autismhealth.modules.health.entity.HealthRecord;
import com.autismhealth.modules.health.service.HealthRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 健康记录控制器。
 */
@RestController
@RequestMapping("/health-records")
@RequiredArgsConstructor
public class HealthRecordController {

    private final HealthRecordService healthRecordService;
    private final EntityReferenceValidator entityReferenceValidator;

    @GetMapping
    public Result<Page<HealthRecord>> page(HealthRecordQueryDTO queryDTO) {
        LambdaQueryWrapper<HealthRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryDTO.getChildId() != null, HealthRecord::getChildId, queryDTO.getChildId())
                .eq(StringUtils.hasText(queryDTO.getEmotionState()), HealthRecord::getEmotionState, queryDTO.getEmotionState())
                .ge(StringUtils.hasText(queryDTO.getRecordDateStart()), HealthRecord::getRecordDate, LocalDate.parse(queryDTO.getRecordDateStart()))
                .le(StringUtils.hasText(queryDTO.getRecordDateEnd()), HealthRecord::getRecordDate, LocalDate.parse(queryDTO.getRecordDateEnd()))
                .orderByDesc(HealthRecord::getRecordDate);
        return Result.success(healthRecordService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper));
    }

    @GetMapping("/{id}")
    public Result<HealthRecord> detail(@PathVariable Long id) {
        return Result.success(healthRecordService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody HealthRecordSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getRecordedBy(), "记录人");
        HealthRecord entity = new HealthRecord();
        BeanUtils.copyProperties(dto, entity);
        return Result.success("新增成功", healthRecordService.save(entity));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody HealthRecordSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getRecordedBy(), "记录人");
        HealthRecord entity = new HealthRecord();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);
        return Result.success("修改成功", healthRecordService.updateById(entity));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", healthRecordService.removeById(id));
    }
}
