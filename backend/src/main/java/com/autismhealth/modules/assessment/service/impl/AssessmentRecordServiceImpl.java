package com.autismhealth.modules.assessment.service.impl;

import com.autismhealth.modules.assessment.entity.AssessmentRecord;
import com.autismhealth.modules.assessment.mapper.AssessmentRecordMapper;
import com.autismhealth.modules.assessment.service.AssessmentRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 评估记录 Service 实现。
 */
@Service
public class AssessmentRecordServiceImpl extends ServiceImpl<AssessmentRecordMapper, AssessmentRecord> implements AssessmentRecordService {
}
