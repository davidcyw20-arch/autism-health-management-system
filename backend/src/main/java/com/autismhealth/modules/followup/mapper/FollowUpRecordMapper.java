package com.autismhealth.modules.followup.mapper;

import com.autismhealth.modules.followup.entity.FollowUpRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 随访记录 Mapper。
 */
@Mapper
public interface FollowUpRecordMapper extends BaseMapper<FollowUpRecord> {
}
