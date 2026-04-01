package com.autismhealth.common.support;

import com.autismhealth.modules.common.mapper.GuardianRelationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 家长账号数据范围辅助类。
 */
@Component
@RequiredArgsConstructor
public class ParentScopeHelper {

    private final GuardianRelationMapper guardianRelationMapper;

    public List<Long> getChildIdsByParentUserId(Long parentUserId) {
        if (parentUserId == null) {
            return Collections.emptyList();
        }
        List<Long> childIds = guardianRelationMapper.selectChildIdsByParentUserId(parentUserId);
        return childIds == null ? Collections.emptyList() : childIds;
    }

    public <T> void applyChildScope(LambdaQueryWrapper<T> wrapper, Long parentUserId,
                                    Consumer<LambdaQueryWrapper<T>> emptyScopeHandler,
                                    Consumer<List<Long>> childScopeHandler) {
        if (parentUserId == null) {
            return;
        }
        List<Long> childIds = getChildIdsByParentUserId(parentUserId);
        if (childIds.isEmpty()) {
            emptyScopeHandler.accept(wrapper);
            return;
        }
        childScopeHandler.accept(childIds);
    }
}
