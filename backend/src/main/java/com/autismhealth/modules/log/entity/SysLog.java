package com.autismhealth.modules.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统日志实体。
 */
@Data
@TableName("sys_log")
public class SysLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String username;
    private String moduleName;
    private String operationType;
    private String requestMethod;
    private String requestUri;
    private String operationDesc;
    private String ipAddress;
    private Integer executionTime;
    private Integer operationStatus;
    private String errorMessage;
    private LocalDateTime createTime;
}
