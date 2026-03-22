package com.autismhealth.modules.child.service.impl;

import com.autismhealth.modules.child.entity.ChildProfile;
import com.autismhealth.modules.child.mapper.ChildProfileMapper;
import com.autismhealth.modules.child.service.ChildProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 儿童档案 Service 实现。
 */
@Service
public class ChildProfileServiceImpl extends ServiceImpl<ChildProfileMapper, ChildProfile> implements ChildProfileService {
}
