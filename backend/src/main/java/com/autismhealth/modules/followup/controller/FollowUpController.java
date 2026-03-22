package com.autismhealth.modules.followup.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.followup.entity.FollowUpRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 随访记录控制器。
 */
@RestController
@RequestMapping("/followups")
public class FollowUpController {

    @GetMapping
    public Result<PageResult<FollowUpRecord>> page() {
        FollowUpRecord one = new FollowUpRecord();
        one.setId(1L);
        one.setChildId(1L);
        one.setFollowUpDate("2025-03-05");
        one.setFollowUpMethod("电话");
        one.setRecoveryProgress("语言表达有小幅提升");
        one.setFamilyFeedback("家庭配合度较高");
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<FollowUpRecord> detail(@PathVariable Long id) {
        FollowUpRecord one = new FollowUpRecord();
        one.setId(id);
        one.setChildId(1L);
        one.setFollowUpDate("2025-03-05");
        one.setFollowUpMethod("电话");
        one.setRecoveryProgress("语言表达有小幅提升");
        one.setFamilyFeedback("家庭配合度较高");
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody FollowUpRecord record) {
        return Result.success("新增成功", "followup created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody FollowUpRecord record) {
        return Result.success("修改成功", "followup updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "followup deleted: " + id);
    }
}
