package com.autismhealth.modules.rehabilitation.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.rehabilitation.entity.RehabilitationRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 康复训练控制器。
 */
@RestController
@RequestMapping("/rehabilitation")
public class RehabilitationController {

    @GetMapping
    public Result<PageResult<RehabilitationRecord>> page() {
        RehabilitationRecord one = new RehabilitationRecord();
        one.setId(1L);
        one.setChildId(1L);
        one.setTrainingDate("2025-03-03");
        one.setTrainingType("语言训练");
        one.setTrainingTheme("主动表达练习");
        one.setDurationMinutes(45);
        one.setPerformanceSummary("可在提示下完成两词句表达");
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<RehabilitationRecord> detail(@PathVariable Long id) {
        RehabilitationRecord one = new RehabilitationRecord();
        one.setId(id);
        one.setChildId(1L);
        one.setTrainingDate("2025-03-03");
        one.setTrainingType("语言训练");
        one.setTrainingTheme("主动表达练习");
        one.setDurationMinutes(45);
        one.setPerformanceSummary("可在提示下完成两词句表达");
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody RehabilitationRecord record) {
        return Result.success("新增成功", "rehabilitation created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody RehabilitationRecord record) {
        return Result.success("修改成功", "rehabilitation updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "rehabilitation deleted: " + id);
    }
}
