package com.autismhealth.modules.log.service.impl;

import com.autismhealth.modules.log.entity.SysLog;
import com.autismhealth.modules.log.mapper.SysLogMapper;
import com.autismhealth.modules.log.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统日志 Service 实现。
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
