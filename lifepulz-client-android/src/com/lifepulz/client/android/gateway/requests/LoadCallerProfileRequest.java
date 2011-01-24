package com.lifepulz.client.android.gateway.requests;

import com.lifepulz.client.android.gateway.Gateway;

public class LoadCallerProfileRequest extends BaseRequest {

    public LoadCallerProfileRequest(Gateway gateway) {
        super(gateway, "get", "/api/me/profile");
    }

}
