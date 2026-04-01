package com.autismhealth.modules.health.mapper;

import com.autismhealth.modules.health.entity.HealthRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 健康记录 Mapper。
 */
@Mapper
public interface HealthRecordMapper extends BaseMapper<HealthRecord> {
}
