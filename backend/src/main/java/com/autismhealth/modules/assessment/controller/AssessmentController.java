package com.autismhealth.modules.assessment.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.assessment.entity.AssessmentRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评估记录控制器。
 */
@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    @GetMapping
    public Result<PageResult<AssessmentRecord>> page() {
        AssessmentRecord one = new AssessmentRecord();
        one.setId(1L);
        one.setChildId(1L);
        one.setAssessmentDate("2025-03-12");
        one.setAssessmentType("阶段评估");
        one.setTotalScore(330.0);
        one.setAssessmentConclusion("语言与社交能力稳步提升");
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<AssessmentRecord> detail(@PathVariable Long id) {
        AssessmentRecord one = new AssessmentRecord();
        one.setId(id);
        one.setChildId(1L);
        one.setAssessmentDate("2025-03-12");
        one.setAssessmentType("阶段评估");
        one.setTotalScore(330.0);
        one.setAssessmentConclusion("语言与社交能力稳步提升");
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody AssessmentRecord record) {
        return Result.success("新增成功", "assessment created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody AssessmentRecord record) {
        return Result.success("修改成功", "assessment updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "assessment deleted: " + id);
    }
}
