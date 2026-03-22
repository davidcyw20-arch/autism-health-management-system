package com.autismhealth.modules.child.controller;

import com.autismhealth.common.result.PageResult;
import com.autismhealth.common.result.Result;
import com.autismhealth.modules.child.entity.ChildProfile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 儿童档案控制器。
 */
@RestController
@RequestMapping("/children")
public class ChildController {

    @GetMapping
    public Result<PageResult<ChildProfile>> page() {
        ChildProfile one = new ChildProfile();
        one.setId(1L);
        one.setChildNo("CH20250001");
        one.setChildName("张晨曦");
        one.setGender(1);
        one.setBirthDate("2018-05-12");
        one.setAutismLevel("中度");
        one.setSchoolName("星光融合幼儿园");
        one.setAddress("杭州市西湖区文三路88号");
        one.setStatus(1);
        return Result.success(new PageResult<>(1L, List.of(one)));
    }

    @GetMapping("/{id}")
    public Result<ChildProfile> detail(@PathVariable Long id) {
        ChildProfile one = new ChildProfile();
        one.setId(id);
        one.setChildNo("CH20250001");
        one.setChildName("张晨曦");
        one.setGender(1);
        one.setBirthDate("2018-05-12");
        one.setAutismLevel("中度");
        one.setSchoolName("星光融合幼儿园");
        one.setAddress("杭州市西湖区文三路88号");
        one.setStatus(1);
        return Result.success(one);
    }

    @PostMapping
    public Result<String> add(@RequestBody ChildProfile childProfile) {
        return Result.success("新增成功", "child created");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id, @RequestBody ChildProfile childProfile) {
        return Result.success("修改成功", "child updated: " + id);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        return Result.success("删除成功", "child deleted: " + id);
    }
}
