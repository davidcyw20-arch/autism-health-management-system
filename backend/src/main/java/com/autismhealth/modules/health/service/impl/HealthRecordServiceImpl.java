package com.autismhealth.modules.health.service.impl;

import com.autismhealth.modules.health.entity.HealthRecord;
import com.autismhealth.modules.health.mapper.HealthRecordMapper;
import com.autismhealth.modules.health.service.HealthRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 健康记录 Service 实现。
 */
@Service
public class HealthRecordServiceImpl extends ServiceImpl<HealthRecordMapper, HealthRecord> implements HealthRecordService {
}
