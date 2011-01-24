package com.lifepulz.client.android.gateway.requests;

import org.apache.http.client.methods.HttpUriRequest;

import android.util.Log;

import com.lifepulz.client.android.gateway.Gateway;

/**
 * Retrieve account information through the member API of the Lifepulz server.
 * 
 * @author joelgrenon
 * 
 */
public class GetAccountStateRequest extends BaseRequest {

    /**
     * Instantiate a valid account state retrieval request.
     * 
     * @param accountId
     */
    public GetAccountStateRequest(Gateway gateway) {
        super(gateway, "get", "/api/member");
    }

    @Override
    public HttpUriRequest buildHttpRequest() throws Exception {
        HttpUriRequest request = null;

        StringBuilder uri = new StringBuilder();
        uri.append(getGateway().getBaseUrl()).append(getRequestUri());

        Log.i("Gateway", "Sending request:" + uri);

        // Use internal service to generate the right HTTP request
        request = createNewHttpRequest(uri.toString());

        return request;
    }

}
