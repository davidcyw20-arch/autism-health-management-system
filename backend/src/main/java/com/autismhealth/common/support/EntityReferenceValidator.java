package com.autismhealth.common.support;

import com.autismhealth.common.exception.BusinessException;
import com.autismhealth.modules.child.service.ChildProfileService;
import com.autismhealth.modules.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 业务实体引用校验器。
 */
@Component
@RequiredArgsConstructor
public class EntityReferenceValidator {

    private final ChildProfileService childProfileService;
    private final SysUserService sysUserService;

    public void validateChildExists(Long childId) {
        if (childId == null) {
            return;
        }
        if (!childProfileService.existsById(childId)) {
            throw new BusinessException("所选儿童档案不存在，请先确认儿童信息是否已建档");
        }
    }

    public void validateUserExists(Long userId, String label) {
        if (userId == null) {
            return;
        }
        if (!sysUserService.existsById(userId)) {
            throw new BusinessException(label + "不存在，请确认关联用户信息是否正确");
        }
    }
}
