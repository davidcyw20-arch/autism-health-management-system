package com.autismhealth.modules.statistics.service.impl;

import com.autismhealth.modules.statistics.mapper.StatisticsMapper;
import com.autismhealth.modules.statistics.service.StatisticsService;
import com.autismhealth.modules.statistics.vo.StatisticsDashboardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 统计分析 Service 实现。
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsMapper statisticsMapper;

    @Override
    public StatisticsDashboardVO getDashboardStatistics() {
        StatisticsDashboardVO vo = new StatisticsDashboardVO();
        vo.setChildCount(statisticsMapper.countChildren());
        vo.setActiveCaseCount(statisticsMapper.countActiveChildren());
        vo.setMonthlyFollowUpCount(statisticsMapper.countMonthlyFollowUps());
        vo.setMonthlyTrainingCount(statisticsMapper.countMonthlyTrainings());
        vo.setHealthTrend(statisticsMapper.healthTrend());
        vo.setMonthlyTrainingTrend(statisticsMapper.monthlyTrainingTrend());
        vo.setMonthlyFollowUpTrend(statisticsMapper.monthlyFollowUpTrend());
        vo.setAssessmentTrend(statisticsMapper.assessmentTrend());
        return vo;
    }
}
