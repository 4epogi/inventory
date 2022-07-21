package com.chepogi.newvtu.exceptions;

public class DeviceTypeException extends RuntimeException {
    public DeviceTypeException(String message){
        super(message);
    }

    public DeviceTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
