package com.lifepulz.client.android.gateway.requests;

import com.lifepulz.client.android.gateway.Gateway;

public class DeleteSelectorRequest extends BaseRequest {

    public DeleteSelectorRequest(Gateway gateway, Long selectorId) {
        super(gateway, "delete", "/api/selector/%d", selectorId);
    }

}
