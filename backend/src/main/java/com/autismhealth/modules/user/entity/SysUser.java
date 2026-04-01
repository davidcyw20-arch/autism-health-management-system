package com.autismhealth.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体。
 */
@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String realName;
    private Integer gender;
    private String phone;
    private String email;
    private String roleCode;
    private Integer status;
    private LocalDateTime lastLoginTime;
    private String remark;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
