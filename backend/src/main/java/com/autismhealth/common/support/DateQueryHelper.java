package com.autismhealth.common.support;

import org.springframework.util.StringUtils;

import java.time.LocalDate;

/**
 * 查询日期参数辅助类。
 */
public final class DateQueryHelper {

    private DateQueryHelper() {
    }

    public static LocalDate parseNullableDate(String value) {
        return StringUtils.hasText(value) ? LocalDate.parse(value) : null;
    }
}
