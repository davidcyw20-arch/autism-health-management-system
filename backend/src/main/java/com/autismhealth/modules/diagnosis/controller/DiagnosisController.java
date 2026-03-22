package com.autismhealth.modules.diagnosis.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.diagnosis.entity.DiagnosisInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 诊断信息控制器。
 */
@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @GetMapping
    public Result<PageResult<DiagnosisInfo>> page() {
        DiagnosisInfo one = new DiagnosisInfo();
        one.setId(1L);
        one.setChildId(1L);
        one.setDiagnosisDate("2024-12-20");
        one.setDiagnosisHospital("杭州市儿童医院");
        one.setDiagnosisDoctor("李建华");
        one.setDiagnosisResult("符合孤独症谱系障碍诊断标准");
        one.setAutismLevel("中度");
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<DiagnosisInfo> detail(@PathVariable Long id) {
        DiagnosisInfo one = new DiagnosisInfo();
        one.setId(id);
        one.setChildId(1L);
        one.setDiagnosisDate("2024-12-20");
        one.setDiagnosisHospital("杭州市儿童医院");
        one.setDiagnosisDoctor("李建华");
        one.setDiagnosisResult("符合孤独症谱系障碍诊断标准");
        one.setAutismLevel("中度");
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody DiagnosisInfo diagnosisInfo) {
        return Result.success("新增成功", "diagnosis created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody DiagnosisInfo diagnosisInfo) {
        return Result.success("修改成功", "diagnosis updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "diagnosis deleted: " + id);
    }
}
