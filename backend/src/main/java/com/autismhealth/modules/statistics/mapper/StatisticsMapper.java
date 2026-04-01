package com.autismhealth.modules.statistics.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 统计分析 Mapper。
 */
@Mapper
public interface StatisticsMapper {

    @Select("SELECT COUNT(*) FROM child_profile WHERE deleted = 0")
    Long countChildren();

    @Select("SELECT COUNT(*) FROM child_profile WHERE deleted = 0 AND status = 1")
    Long countActiveChildren();

    @Select("SELECT COUNT(*) FROM follow_up_record WHERE DATE_FORMAT(follow_up_date, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')")
    Long countMonthlyFollowUps();

    @Select("SELECT COUNT(*) FROM rehabilitation_record WHERE DATE_FORMAT(training_date, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m')")
    Long countMonthlyTrainings();

    @Select("SELECT DATE_FORMAT(record_date, '%m-%d') AS label, COUNT(*) AS value FROM health_record GROUP BY record_date ORDER BY record_date DESC LIMIT 7")
    List<Map<String, Object>> healthTrend();

    @Select("SELECT DATE_FORMAT(training_date, '%Y-%m') AS label, COUNT(*) AS value FROM rehabilitation_record GROUP BY DATE_FORMAT(training_date, '%Y-%m') ORDER BY label")
    List<Map<String, Object>> monthlyTrainingTrend();

    @Select("SELECT DATE_FORMAT(follow_up_date, '%Y-%m') AS label, COUNT(*) AS value FROM follow_up_record GROUP BY DATE_FORMAT(follow_up_date, '%Y-%m') ORDER BY label")
    List<Map<String, Object>> monthlyFollowUpTrend();

    @Select("SELECT DATE_FORMAT(assessment_date, '%Y-%m-%d') AS label, ROUND(AVG(total_score), 2) AS value FROM assessment_record GROUP BY assessment_date ORDER BY assessment_date")
    List<Map<String, Object>> assessmentTrend();
}
