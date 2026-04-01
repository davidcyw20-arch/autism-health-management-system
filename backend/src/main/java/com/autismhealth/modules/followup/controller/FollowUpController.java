package com.autismhealth.modules.followup.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.common.support.DateQueryHelper;
import com.autismhealth.common.support.EntityReferenceValidator;
import com.autismhealth.common.support.ParentScopeHelper;
import com.autismhealth.modules.followup.dto.FollowUpQueryDTO;
import com.autismhealth.modules.followup.dto.FollowUpSaveDTO;
import com.autismhealth.modules.followup.entity.FollowUpRecord;
import com.autismhealth.modules.followup.service.FollowUpRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 随访记录控制器。
 */
@RestController
@RequestMapping("/followups")
@RequiredArgsConstructor
public class FollowUpController {

    private final FollowUpRecordService followUpRecordService;
    private final EntityReferenceValidator entityReferenceValidator;
    private final ParentScopeHelper parentScopeHelper;

    @GetMapping
    public Result<Page<FollowUpRecord>> page(FollowUpQueryDTO queryDTO) {
        LocalDate followUpDateStart = DateQueryHelper.parseNullableDate(queryDTO.getFollowUpDateStart());
        LocalDate followUpDateEnd = DateQueryHelper.parseNullableDate(queryDTO.getFollowUpDateEnd());
        LambdaQueryWrapper<FollowUpRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryDTO.getChildId() != null, FollowUpRecord::getChildId, queryDTO.getChildId())
                .eq(StringUtils.hasText(queryDTO.getFollowUpMethod()), FollowUpRecord::getFollowUpMethod, queryDTO.getFollowUpMethod())
                .ge(followUpDateStart != null, FollowUpRecord::getFollowUpDate, followUpDateStart)
                .le(followUpDateEnd != null, FollowUpRecord::getFollowUpDate, followUpDateEnd);
        parentScopeHelper.applyChildScope(wrapper, queryDTO.getParentUserId(),
                query -> query.apply("1 = 0"),
                childIds -> wrapper.in(FollowUpRecord::getChildId, childIds));
        wrapper.orderByDesc(FollowUpRecord::getFollowUpDate);
        return Result.success(followUpRecordService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper));
    }

    @GetMapping("/{id}")
    public Result<FollowUpRecord> detail(@PathVariable Long id) {
        return Result.success(followUpRecordService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody FollowUpSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getFollowUpPerson(), "随访人");
        FollowUpRecord entity = new FollowUpRecord();
        BeanUtils.copyProperties(dto, entity);
        return Result.success("新增成功", followUpRecordService.save(entity));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody FollowUpSaveDTO dto) {
        entityReferenceValidator.validateChildExists(dto.getChildId());
        entityReferenceValidator.validateUserExists(dto.getFollowUpPerson(), "随访人");
        FollowUpRecord entity = new FollowUpRecord();
        BeanUtils.copyProperties(dto, entity);
        entity.setId(id);
        return Result.success("修改成功", followUpRecordService.updateById(entity));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", followUpRecordService.removeById(id));
    }
}
