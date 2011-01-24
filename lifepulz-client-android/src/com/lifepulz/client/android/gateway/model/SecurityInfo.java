package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;

/**
 * Wrap a security information block
 * 
 * "security": { "minKarma": 1, notifications: { "sms":"5145555555",
 * "email":"test@test.com" } }
 * 
 * @author joelgrenon
 * 
 */
public class SecurityInfo extends JsonBackedModel {

    public SecurityInfo(HttpEntity body) {
        super(body);
    }

    public Integer getMinKarma() {
        return getInteger("minKarma");
    }

    public void setMinKarma(Integer minKarma) {
        setInteger("minKarma", minKarma);
    }

    public NotificationDetails getNotificationDetails() {
        return getObject("notifications", NotificationDetails.class);
    }

    public void setNotificationDetails(NotificationDetails nd) {
        setObject("notifications", nd);
    }
}
