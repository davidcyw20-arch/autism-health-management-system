package com.autismhealth.modules.statistics.controller;

import com.autismhealth.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 统计分析控制器。
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @GetMapping("/dashboard")
    public Result<Map<String, Object>> dashboard() {
        Map<String, Object> data = Map.of(
                "childCount", 3,
                "activeCaseCount", 3,
                "monthlyFollowUpCount", 3,
                "monthlyTrainingCount", 5,
                "healthTrend", List.of(6, 8, 7, 9, 10, 11, 8),
                "assessmentTrend", List.of(257, 330, 379)
        );
        return Result.success(data);
    }
}
