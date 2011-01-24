package com.lifepulz.client.android.gateway;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import android.util.Log;

import com.lifepulz.TestData;
import com.lifepulz.client.android.gateway.Gateway;
import com.lifepulz.client.android.gateway.GatewayContext;
import com.lifepulz.client.android.gateway.model.Profile;

public class GatewayTest extends AndroidTestCase {

    // public static final String URL = "http://192.168.2.3:9000";
    public static final String URL = "http://www.lifepulz.com";

    public void testEstablishContext() throws Throwable {
        Gateway gateway = new Gateway(URL);

        Log.d("testEstablishContext", "Establishing gateway context");

        GatewayContext context = gateway.establishContext(
                TestData.TEST_API_KEY, TestData.TEST_ACCOUNT,
                TestData.TEST_ACCESS_CODE);
        Assert.assertNotNull(context);
        Assert.assertEquals(TestData.TEST_ACCESS_CODE, context.getAccessCode());
        Assert.assertEquals(TestData.TEST_ACCOUNT, context.getCallerId());
        Assert.assertEquals(TestData.TEST_API_KEY, context.getClientKey());
    }

    public void testCreateProfile() throws Throwable {
        Gateway gateway = new Gateway(URL);

        GatewayContext context = gateway.establishContext(
                TestData.TEST_API_KEY, TestData.TEST_ACCOUNT,
                TestData.TEST_ACCESS_CODE);

        Profile profile = gateway.createProfile(context,
                TestData.TEST_PROFILE_ALIAS);
        Assert.assertNotNull(profile);
    }

    public void testLoadProfile() throws Throwable {
        Gateway gateway = new Gateway(URL);

        GatewayContext context = gateway.establishContext(
                TestData.TEST_API_KEY, TestData.TEST_ACCOUNT,
                TestData.TEST_ACCESS_CODE);

        Profile profile = gateway.loadProfile(context,
                TestData.TEST_PROFILE_ALIAS);
        Assert.assertNotNull(profile);
    }
}
