package com.lifepulz.client.android.gateway;

import java.util.HashMap;
import java.util.Map;

public class GatewayContext {

    private String clientKey;
    private String callerId;
    private String accessCode;
    private Map<String, Object> properties = new HashMap<String, Object>();

    public void setClientKey(String apiKey) {
        this.clientKey = apiKey;
    }

    public void setCallerId(String email) {
        this.callerId = email;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public void setProperty(String name, Object value) {
        this.properties.put(name, value);
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getCallerId() {
        return callerId;
    }

    public String getAccessCode() {
        return accessCode;
    }

}
