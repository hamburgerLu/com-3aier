package com.threeAier.app.common;

public class ThreeAierRuntimeException extends RuntimeException{

    Integer code;
    String message;

    public ThreeAierRuntimeException(String message) {
        this.message = message;
    }

    public ThreeAierRuntimeException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ThreeAierRuntimeException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ThreeAierRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public ThreeAierRuntimeException(Throwable cause) {
        super(cause);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
