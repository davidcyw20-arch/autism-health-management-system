package com.autismhealth.modules.log.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统日志控制器。
 */
@RestController
@RequestMapping("/logs")
public class LogController {

    @GetMapping
    public Result<PageResult<Map<String, Object>>> page(@RequestParam(defaultValue = "1") Integer current,
                                                        @RequestParam(defaultValue = "10") Integer size) {
        List<Map<String, Object>> records = List.of(
                Map.of("id", 1, "moduleName", "用户管理", "operationType", "新增", "username", "admin", "operationStatus", 1),
                Map.of("id", 2, "moduleName", "儿童档案管理", "operationType", "查询", "username", "doctor01", "operationStatus", 1)
        );
        return Result.success(new PageResult<>((long) records.size(), records));
    }
}
