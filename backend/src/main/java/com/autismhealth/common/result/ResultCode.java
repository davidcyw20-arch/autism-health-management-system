package com.autismhealth.common.result;

/**
 * 统一返回码定义。
 */
public final class ResultCode {

    private ResultCode() {
    }

    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int SERVER_ERROR = 500;
}
