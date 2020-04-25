package com.platform.mall.common;

//返回值编码
public enum ResultCode {

    SUCCESS(1, "操作成功"),
    FAILED(-1, "操作失败"),
    VALIDATE_FAILED(2, "参数检验失败"),
    TIME_OUT(3, "会话过期");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
