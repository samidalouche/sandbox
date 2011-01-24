package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;

/**
 * Abstract the basic account state.
 * 
 * @author joelgrenon
 * 
 */
public class AccountState extends JsonBackedModel {

    public AccountState(HttpEntity body) {
        super(body);
    }

    public boolean isActive() {
        return true;
    }

}
