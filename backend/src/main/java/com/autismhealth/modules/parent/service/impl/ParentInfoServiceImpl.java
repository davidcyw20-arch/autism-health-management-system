package com.autismhealth.modules.parent.service.impl;

import com.autismhealth.modules.parent.entity.ParentInfo;
import com.autismhealth.modules.parent.mapper.ParentInfoMapper;
import com.autismhealth.modules.parent.service.ParentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 家长信息 Service 实现。
 */
@Service
public class ParentInfoServiceImpl extends ServiceImpl<ParentInfoMapper, ParentInfo> implements ParentInfoService {
}
