package com.lifepulz.client.android.gateway.requests;

import com.lifepulz.client.android.gateway.Gateway;


/**
 * Retrieve a list of selectors from the backend
 * 
 * @author joelgrenon
 *
 */
public class ListSelectorsRequest extends BaseRequest {

	public ListSelectorsRequest(Gateway gateway) {
		super(gateway, "get", "/api/selectors");
	}

}
