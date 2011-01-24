package com.lifepulz.client.android.gateway.requests;

import com.lifepulz.client.android.gateway.Gateway;

public class LoadProfileRequest extends BaseRequest {

    public LoadProfileRequest(Gateway gateway, String alias) {
        super(gateway, "get", "/api/profile/%s", alias);
    }

}
