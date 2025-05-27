package com.cas.sur.tout.urgents.exception;

import lombok.Getter;

@Getter
public class ZoneNotFoundException extends RuntimeException {
    private ErrorCodes errorCode;

    public ZoneNotFoundException(String message) {
        super(message);
    }

    public ZoneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZoneNotFoundException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ZoneNotFoundException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
