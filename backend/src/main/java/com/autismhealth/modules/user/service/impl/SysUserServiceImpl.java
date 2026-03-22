package com.autismhealth.modules.user.service.impl;

import com.autismhealth.modules.user.entity.SysUser;
import com.autismhealth.modules.user.mapper.SysUserMapper;
import com.autismhealth.modules.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户 Service 实现。
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
