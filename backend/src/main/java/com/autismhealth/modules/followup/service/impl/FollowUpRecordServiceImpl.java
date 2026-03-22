package com.autismhealth.modules.followup.service.impl;

import com.autismhealth.modules.followup.entity.FollowUpRecord;
import com.autismhealth.modules.followup.mapper.FollowUpRecordMapper;
import com.autismhealth.modules.followup.service.FollowUpRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 随访记录 Service 实现。
 */
@Service
public class FollowUpRecordServiceImpl extends ServiceImpl<FollowUpRecordMapper, FollowUpRecord> implements FollowUpRecordService {
}
