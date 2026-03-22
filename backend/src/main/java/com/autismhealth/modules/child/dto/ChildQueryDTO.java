package com.autismhealth.modules.child.dto;

import lombok.Data;

/**
 * 儿童档案查询参数。
 */
@Data
public class ChildQueryDTO {
    private Integer current = 1;
    private Integer size = 10;
    private String childName;
    private String childNo;
    private String autismLevel;
    private Integer status;
}
