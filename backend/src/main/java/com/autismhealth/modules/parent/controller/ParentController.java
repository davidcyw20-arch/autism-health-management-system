package com.autismhealth.modules.parent.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.parent.entity.ParentInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家长信息控制器。
 */
@RestController
@RequestMapping("/parents")
public class ParentController {

    @GetMapping
    public Result<PageResult<ParentInfo>> page() {
        ParentInfo one = new ParentInfo();
        one.setId(1L);
        one.setParentName("张敏");
        one.setRelationType("母亲");
        one.setPhone("13800000004");
        one.setOccupation("会计");
        one.setAddress("杭州市西湖区文三路88号");
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<ParentInfo> detail(@PathVariable Long id) {
        ParentInfo one = new ParentInfo();
        one.setId(id);
        one.setParentName("张敏");
        one.setRelationType("母亲");
        one.setPhone("13800000004");
        one.setOccupation("会计");
        one.setAddress("杭州市西湖区文三路88号");
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody ParentInfo parentInfo) {
        return Result.success("新增成功", "parent created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody ParentInfo parentInfo) {
        return Result.success("修改成功", "parent updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "parent deleted: " + id);
    }
}
