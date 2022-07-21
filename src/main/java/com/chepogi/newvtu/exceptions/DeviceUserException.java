package com.chepogi.newvtu.exceptions;

public class DeviceUserException extends RuntimeException {

    public DeviceUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceUserException(String message) {
        super(message);
    }
}
