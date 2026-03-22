package com.autismhealth.modules.child.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.modules.child.dto.ChildQueryDTO;
import com.autismhealth.modules.child.entity.ChildProfile;
import com.autismhealth.modules.child.service.ChildProfileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 儿童档案控制器。
 */
@RestController
@RequestMapping("/children")
@RequiredArgsConstructor
public class ChildController {

    private final ChildProfileService childProfileService;

    @GetMapping
    public Result<Page<ChildProfile>> page(ChildQueryDTO queryDTO) {
        LambdaQueryWrapper<ChildProfile> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(queryDTO.getChildName()), ChildProfile::getChildName, queryDTO.getChildName())
                .like(StringUtils.hasText(queryDTO.getChildNo()), ChildProfile::getChildNo, queryDTO.getChildNo())
                .eq(StringUtils.hasText(queryDTO.getAutismLevel()), ChildProfile::getAutismLevel, queryDTO.getAutismLevel())
                .eq(queryDTO.getStatus() != null, ChildProfile::getStatus, queryDTO.getStatus())
                .orderByDesc(ChildProfile::getCreateTime);
        Page<ChildProfile> page = childProfileService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<ChildProfile> detail(@PathVariable Long id) {
        return Result.success(childProfileService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@RequestBody ChildProfile childProfile) {
        return Result.success("新增成功", childProfileService.save(childProfile));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @RequestBody ChildProfile childProfile) {
        childProfile.setId(id);
        return Result.success("修改成功", childProfileService.updateById(childProfile));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", childProfileService.removeById(id));
    }
}
