package com.lifepulz.client.android;

import android.app.Application;

import com.lifepulz.client.android.gateway.Gateway;
import com.lifepulz.client.android.gateway.GatewayContext;
import com.lifepulz.client.android.gateway.model.Profile;

public class Lifepulz extends Application {

    private GatewayContext gatewayContext;
    private Profile profile;
    private Gateway gateway;

    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    public void setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
    }

    public void setMemberProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getMemberProfile() {
        return profile;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Gateway getGateway() {
        return gateway;
    }
    
}
