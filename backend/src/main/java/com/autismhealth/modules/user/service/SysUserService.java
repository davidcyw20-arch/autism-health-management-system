package com.autismhealth.modules.user.service;

import com.autismhealth.modules.user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户 Service。
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 判断用户是否存在。
     */
    default boolean existsById(Long id) {
        return id != null && this.lambdaQuery().eq(SysUser::getId, id).exists();
    }
}
