package com.autismhealth.modules.parent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 家长信息实体。
 */
@Data
@TableName("parent_info")
public class ParentInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String parentName;
    private Integer gender;
    private String relationType;
    private String phone;
    private String wechat;
    private String occupation;
    private String educationLevel;
    private String address;
    private String emergencyContact;
    private String emergencyPhone;
    private String remark;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
