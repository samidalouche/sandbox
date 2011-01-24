package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;

/**
 * Transport object wrapping the profile concept in Lifepulz.
 * 
 * @author joelgrenon
 * 
 */
public class Profile extends JsonBackedModel {

    public Profile(HttpEntity body) {
        super(body);
    }

    public Profile() {
    }

    public String getAlias() {
        return getString("alias");
    }

    public void setAlias(String alias) {
        setString("alias", alias);
    }
}
