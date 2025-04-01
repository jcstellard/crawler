//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.nb.pachong.entity;

public enum ResultCodeEnum {
    SUCCESS(200, "SUCCESS"),
    REDIRECT(301, "REDIRECT"),
    ACCESS_DENIED(401, "ACCESS_DENIED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    CONFLICT(409, "CONFLICT"),
    ERROR(500, "ERROR");

    private int code;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
