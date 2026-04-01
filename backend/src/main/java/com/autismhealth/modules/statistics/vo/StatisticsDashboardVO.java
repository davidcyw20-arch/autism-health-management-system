package com.autismhealth.modules.statistics.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 统计分析首页数据。
 */
@Data
public class StatisticsDashboardVO {
    private Long childCount;
    private Long activeCaseCount;
    private Long monthlyFollowUpCount;
    private Long monthlyTrainingCount;
    private List<Map<String, Object>> healthTrend;
    private List<Map<String, Object>> monthlyTrainingTrend;
    private List<Map<String, Object>> monthlyFollowUpTrend;
    private List<Map<String, Object>> assessmentTrend;
}
