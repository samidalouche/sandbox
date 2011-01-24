package com.lifepulz.client.android.gateway.requests;

import com.lifepulz.client.android.gateway.Gateway;
import com.lifepulz.client.android.gateway.model.Presence;

public class UpdatePresenceRequest extends BaseRequest {

    public UpdatePresenceRequest(Gateway gateway, Presence presence) {
        super(gateway, "post", "/api/presence");
        setContent(presence);
    }

}
