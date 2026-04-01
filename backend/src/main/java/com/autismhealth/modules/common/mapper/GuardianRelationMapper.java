package com.autismhealth.modules.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 家长与儿童关联查询 Mapper。
 */
@Mapper
public interface GuardianRelationMapper {

    @Select("""
            SELECT cgr.child_id
            FROM child_guardian_rel cgr
            INNER JOIN parent_info pi ON pi.id = cgr.parent_id
            WHERE pi.user_id = #{userId}
              AND pi.deleted = 0
            """)
    List<Long> selectChildIdsByParentUserId(@Param("userId") Long userId);
}
