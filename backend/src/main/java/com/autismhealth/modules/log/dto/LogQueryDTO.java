package com.autismhealth.modules.log.dto;

import lombok.Data;

/**
 * 系统日志查询参数。
 */
@Data
public class LogQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private String moduleName;
    private String username;
    private Integer operationStatus;
}
