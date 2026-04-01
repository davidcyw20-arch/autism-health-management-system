package com.autismhealth.modules.log.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.modules.log.dto.LogQueryDTO;
import com.autismhealth.modules.log.entity.SysLog;
import com.autismhealth.modules.log.service.SysLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志控制器。
 */
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final SysLogService sysLogService;

    @GetMapping
    public Result<Page<SysLog>> page(LogQueryDTO queryDTO) {
        LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(queryDTO.getModuleName()), SysLog::getModuleName, queryDTO.getModuleName())
                .like(StringUtils.hasText(queryDTO.getUsername()), SysLog::getUsername, queryDTO.getUsername())
                .eq(queryDTO.getOperationStatus() != null, SysLog::getOperationStatus, queryDTO.getOperationStatus())
                .orderByDesc(SysLog::getCreateTime);
        Page<SysLog> page = sysLogService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper);
        return Result.success(page);
    }
}
