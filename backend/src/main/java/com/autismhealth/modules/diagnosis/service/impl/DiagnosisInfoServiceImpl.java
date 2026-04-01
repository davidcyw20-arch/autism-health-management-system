package com.autismhealth.modules.diagnosis.service.impl;

import com.autismhealth.modules.diagnosis.entity.DiagnosisInfo;
import com.autismhealth.modules.diagnosis.mapper.DiagnosisInfoMapper;
import com.autismhealth.modules.diagnosis.service.DiagnosisInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 诊断信息 Service 实现。
 */
@Service
public class DiagnosisInfoServiceImpl extends ServiceImpl<DiagnosisInfoMapper, DiagnosisInfo> implements DiagnosisInfoService {
}
