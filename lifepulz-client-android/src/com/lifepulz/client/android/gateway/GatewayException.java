package com.lifepulz.client.android.gateway;

public class GatewayException extends Exception {
    private static final long serialVersionUID = 1L;

    public GatewayException(String msg) {
        super(msg);
    }

    public GatewayException(Throwable cause) {
        super(cause);
    }

}
