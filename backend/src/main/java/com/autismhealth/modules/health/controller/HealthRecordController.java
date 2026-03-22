package com.autismhealth.modules.health.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.health.entity.HealthRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 健康记录控制器。
 */
@RestController
@RequestMapping("/health-records")
public class HealthRecordController {

    @GetMapping
    public Result<PageResult<HealthRecord>> page() {
        HealthRecord one = new HealthRecord();
        one.setId(1L);
        one.setChildId(1L);
        one.setRecordDate("2025-03-01");
        one.setTemperature(36.5);
        one.setHeartRate(92);
        one.setSleepHours(9.0);
        one.setEmotionState("平稳");
        one.setAbnormalSymptom("无");
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<HealthRecord> detail(@PathVariable Long id) {
        HealthRecord one = new HealthRecord();
        one.setId(id);
        one.setChildId(1L);
        one.setRecordDate("2025-03-01");
        one.setTemperature(36.5);
        one.setHeartRate(92);
        one.setSleepHours(9.0);
        one.setEmotionState("平稳");
        one.setAbnormalSymptom("无");
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody HealthRecord healthRecord) {
        return Result.success("新增成功", "health created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody HealthRecord healthRecord) {
        return Result.success("修改成功", "health updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "health deleted: " + id);
    }
}
