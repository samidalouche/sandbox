package com.lifepulz.client.android.gateway.requests;

import com.lifepulz.client.android.gateway.Gateway;

/**
 * Package an HTTP request to retrieve the pulz associated with a specific selector
 * 
 * @author joelgrenon
 *
 */
public class ActivateSelectorRequest extends BaseRequest {

    public ActivateSelectorRequest(Gateway gateway, Long selectorId) {
        super(gateway, "get", "/api/selector/%d", selectorId);
    }

}
