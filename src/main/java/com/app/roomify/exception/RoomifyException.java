package com.app.roomify.exception;

public class RoomifyException extends RuntimeException {

    private final AppErrors errorCode;

    public RoomifyException(AppErrors errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public RoomifyException(AppErrors errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }



}
