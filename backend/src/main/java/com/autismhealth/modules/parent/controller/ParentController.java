package com.autismhealth.modules.parent.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.modules.parent.dto.ParentQueryDTO;
import com.autismhealth.modules.parent.dto.ParentSaveDTO;
import com.autismhealth.modules.parent.entity.ParentInfo;
import com.autismhealth.modules.parent.service.ParentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 家长信息控制器。
 */
@RestController
@RequestMapping("/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentInfoService parentInfoService;

    @GetMapping
    public Result<Page<ParentInfo>> page(ParentQueryDTO queryDTO) {
        LambdaQueryWrapper<ParentInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(queryDTO.getParentName()), ParentInfo::getParentName, queryDTO.getParentName())
                .like(StringUtils.hasText(queryDTO.getPhone()), ParentInfo::getPhone, queryDTO.getPhone())
                .eq(StringUtils.hasText(queryDTO.getRelationType()), ParentInfo::getRelationType, queryDTO.getRelationType())
                .orderByDesc(ParentInfo::getCreateTime);
        Page<ParentInfo> page = parentInfoService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<ParentInfo> detail(@PathVariable Long id) {
        return Result.success(parentInfoService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody ParentSaveDTO dto) {
        ParentInfo parentInfo = new ParentInfo();
        BeanUtils.copyProperties(dto, parentInfo);
        return Result.success("新增成功", parentInfoService.save(parentInfo));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody ParentSaveDTO dto) {
        ParentInfo parentInfo = new ParentInfo();
        BeanUtils.copyProperties(dto, parentInfo);
        parentInfo.setId(id);
        return Result.success("修改成功", parentInfoService.updateById(parentInfo));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", parentInfoService.removeById(id));
    }
}
