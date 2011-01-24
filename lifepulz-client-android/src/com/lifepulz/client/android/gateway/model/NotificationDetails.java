package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;

/**
 * notifications: { "sms":"5145555555", "email":"test@test.com" }
 * 
 * @author joelgrenon
 * 
 */
public class NotificationDetails extends JsonBackedModel {

    public NotificationDetails(HttpEntity body) {
        super(body);
    }

    public String getSms() {
        return getString("sms");
    }

    public void setSms(String sms) {
        setString("sms", sms);
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        setString("email", email);
    }
}
