package com.autismhealth.modules.diagnosis.controller;

import com.autismhealth.common.result.Result;
import com.autismhealth.modules.diagnosis.dto.DiagnosisQueryDTO;
import com.autismhealth.modules.diagnosis.dto.DiagnosisSaveDTO;
import com.autismhealth.modules.diagnosis.entity.DiagnosisInfo;
import com.autismhealth.modules.diagnosis.service.DiagnosisInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 诊断信息控制器。
 */
@RestController
@RequestMapping("/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisInfoService diagnosisInfoService;

    @GetMapping
    public Result<Page<DiagnosisInfo>> page(DiagnosisQueryDTO queryDTO) {
        LambdaQueryWrapper<DiagnosisInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(queryDTO.getChildId() != null, DiagnosisInfo::getChildId, queryDTO.getChildId())
                .like(StringUtils.hasText(queryDTO.getDiagnosisHospital()), DiagnosisInfo::getDiagnosisHospital, queryDTO.getDiagnosisHospital())
                .eq(StringUtils.hasText(queryDTO.getAutismLevel()), DiagnosisInfo::getAutismLevel, queryDTO.getAutismLevel())
                .orderByDesc(DiagnosisInfo::getDiagnosisDate);
        Page<DiagnosisInfo> page = diagnosisInfoService.page(new Page<>(queryDTO.getCurrent(), queryDTO.getSize()), wrapper);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<DiagnosisInfo> detail(@PathVariable Long id) {
        return Result.success(diagnosisInfoService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@Valid @RequestBody DiagnosisSaveDTO dto) {
        DiagnosisInfo diagnosisInfo = new DiagnosisInfo();
        BeanUtils.copyProperties(dto, diagnosisInfo);
        return Result.success("新增成功", diagnosisInfoService.save(diagnosisInfo));
    }

    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable Long id, @Valid @RequestBody DiagnosisSaveDTO dto) {
        DiagnosisInfo diagnosisInfo = new DiagnosisInfo();
        BeanUtils.copyProperties(dto, diagnosisInfo);
        diagnosisInfo.setId(id);
        return Result.success("修改成功", diagnosisInfoService.updateById(diagnosisInfo));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success("删除成功", diagnosisInfoService.removeById(id));
    }
}
