package com.autismhealth.modules.log.mapper;

import com.autismhealth.modules.log.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志 Mapper。
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {
}
