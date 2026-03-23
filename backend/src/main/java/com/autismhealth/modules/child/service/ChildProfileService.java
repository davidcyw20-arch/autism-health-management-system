package com.autismhealth.modules.child.service;

import com.autismhealth.modules.child.entity.ChildProfile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 儿童档案 Service。
 */
public interface ChildProfileService extends IService<ChildProfile> {

    /**
     * 判断儿童档案是否存在。
     */
    default boolean existsById(Long id) {
        return id != null && this.lambdaQuery().eq(ChildProfile::getId, id).exists();
    }
}
