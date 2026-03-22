package com.autismhealth.modules.statistics.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.modules.statistics.service.StatisticsService;
import com.autismhealth.modules.statistics.vo.StatisticsDashboardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计分析控制器。
 */
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/dashboard")
    public Result<StatisticsDashboardVO> dashboard() {
        return Result.success(statisticsService.getDashboardStatistics());
    }
}
