package com.lifepulz.client.android.gateway;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.lifepulz.client.android.gateway.model.AccountState;
import com.lifepulz.client.android.gateway.model.ModelObject;
import com.lifepulz.client.android.gateway.model.Presence;
import com.lifepulz.client.android.gateway.model.Profile;
import com.lifepulz.client.android.gateway.model.Pulz;
import com.lifepulz.client.android.gateway.model.Selector;
import com.lifepulz.client.android.gateway.model.SelectorList;
import com.lifepulz.client.android.gateway.requests.ActivateSelectorRequest;
import com.lifepulz.client.android.gateway.requests.BaseRequest;
import com.lifepulz.client.android.gateway.requests.DeleteSelectorRequest;
import com.lifepulz.client.android.gateway.requests.GetAccountStateRequest;
import com.lifepulz.client.android.gateway.requests.ListSelectorsRequest;
import com.lifepulz.client.android.gateway.requests.LoadCallerProfileRequest;
import com.lifepulz.client.android.gateway.requests.LoadProfileRequest;
import com.lifepulz.client.android.gateway.requests.UpdatePresenceRequest;

/**
 * Abstract the Lifepulz server for Android entities. This implementation uses Android HTTP client to access the Lifepulz server.
 * 
 * NOTE: You'll need the Internet permission in your Android manifest if you instantiate this class.
 * 
 * @author joelgrenon
 * 
 */
public class Gateway {

    public static final String LIFEPULZ_ACCESS_CODE_HEADER = "Lifepulz-AccessCode";
    public static final String LIFEPULZ_CALLER_ID_HEADER = "Lifepulz-CallerId";
    public static final String LIFEPULZ_API_KEY_HEADER = "Lifepulz-ApiKey";
    private String baseUrl;

    /**
     * Construct a new gateway instance.
     * 
     * @param baseUrl
     *            The base portion used to construct all requests. something like : http://lifepulz.appspot.com
     */
    public Gateway(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Establish the current user context by verifying credentials with the server.
     * 
     * NOTE: This will change in future version. For now, we're using a simple access code mechanism but we will move to better authentication mechanisms like
     * Google account or Facebook Connect.
     * 
     * @param apiKey
     *            The key associated with the client application.
     * @param email
     *            The email address of the current application user.
     * @param accessCode
     *            The access code provided by Lifepulz to this user.
     * @return A valid GatewayContext instance usable in other calls through this gateway.
     * @throws GatewayException
     */
    public GatewayContext establishContext(String apiKey, String email, String accessCode) throws GatewayException {
        GatewayContext context = new GatewayContext();

        Log.d(Gateway.class.getName(), String.format("Establishing context using apiKey:%s, email:%s, accessCode:%s", apiKey, email, accessCode));

        // Initialize the context
        context.setClientKey(apiKey);
        context.setCallerId(email);
        context.setAccessCode(accessCode);

        // Validate the account
        AccountState account = sendRequest(context, new GetAccountStateRequest(this), AccountState.class);
        if (account.isActive())
            context.setProperty("account", account);
        else
            throw new GatewayException("invalid.account");

        return context;
    }

    /**
     * Send a request without handling the result. useful for delete operations.  
     * 
     * @param context
     * @param request
     * @throws GatewayException
     */
    public void sendRequest(GatewayContext context, Request request) throws GatewayException {

        // Switch to use the internal version
        BaseRequest internalRequest = (BaseRequest) request;

        try {
            // Create the HTTP request
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest httpReq = internalRequest.buildHttpRequest();
            httpReq.setHeader("user-agent", "Lifepulz/Android/1");

            Log.d(Gateway.class.getName(), "Request URI:" + httpReq.getURI().toString());

            // Append all HTTP headers received from the operation configuration
            httpReq.addHeader(LIFEPULZ_API_KEY_HEADER, context.getClientKey());
            httpReq.addHeader(LIFEPULZ_ACCESS_CODE_HEADER, context.getAccessCode());
            httpReq.addHeader(LIFEPULZ_CALLER_ID_HEADER, context.getCallerId());

            // Send the HTTP request
            client.execute(httpReq);

        } catch (Exception ex) {
            Log.e(Gateway.class.getName(), ex.getLocalizedMessage(), ex);
            if (ex.getCause() instanceof GatewayException)
                throw (GatewayException) ex.getCause();
            else
                throw new GatewayException(ex.getCause());
        }
    }

    /**
     * Send a specific request to the server.
     * 
     * @param context
     * @param getAccountStateRequest
     */
    private <T extends ModelObject> T sendRequest(GatewayContext context, Request request, final Class<T> returnClass) throws GatewayException {
        T result = null;

        // Switch to use the internal version
        BaseRequest internalRequest = (BaseRequest) request;

        try {
            // Create the HTTP request
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest httpReq = internalRequest.buildHttpRequest();
            httpReq.setHeader("user-agent", "Lifepulz/Android/1");

            Log.d(Gateway.class.getName(), "Request URI:" + httpReq.getURI().toString());

            // Append all HTTP headers received from the operation configuration
            httpReq.addHeader(LIFEPULZ_API_KEY_HEADER, context.getClientKey());
            httpReq.addHeader(LIFEPULZ_ACCESS_CODE_HEADER, context.getAccessCode());
            httpReq.addHeader(LIFEPULZ_CALLER_ID_HEADER, context.getCallerId());

            // Send the HTTP request
            result = client.execute(httpReq, new ResponseHandler<T>() {

                public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    T result = null;

                    try {
                        // Make sure we have a success
                        if (response.getStatusLine().getStatusCode() < 399) {
                            Constructor<T> cstor = returnClass.getConstructor(HttpEntity.class);
                            if (cstor != null)
                                result = cstor.newInstance(response.getEntity());
                        } else
                            throw new GatewayException(response.getStatusLine().toString());

                    } catch (Exception e) {
                        throw new ClientProtocolException(e);
                    }

                    return result;
                }
            });

        } catch (Exception ex) {
            Log.e(Gateway.class.getName(), ex.getLocalizedMessage(), ex);
            if (ex.getCause() instanceof GatewayException)
                throw (GatewayException) ex.getCause();
            else
                throw new GatewayException(ex.getCause());
        }

        return result;
    }

    /**
     * Load the profile for the given alias.
     * 
     * @param context
     *            An establish gateway context
     * @param alias
     *            The alias of the profile to load
     * @return a Profile instance containing the detail of this user profile.
     * @throws GatewayException
     */
    public Profile loadProfile(GatewayContext context, String alias) throws GatewayException {
        Profile profile = null;

        // Load the profile for this alias
        profile = sendRequest(context, new LoadProfileRequest(this, alias), Profile.class);

        return profile;
    }
    
    /**
     * Load the profile associated with the current caller.
     * 
     * @param context
     *            An establish gateway context
     * @return a Profile instance containing the detail of this user profile.
     * @throws GatewayException 
     */
    public Profile loadProfile(GatewayContext context) throws GatewayException {
        Profile profile = null;

        // Load the profile for this alias
        profile = sendRequest(context, new LoadCallerProfileRequest(this), Profile.class);

        return profile;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Profile createProfile(GatewayContext context, String alias) {
        Profile profile = new Profile();
        profile.setAlias(alias);

        // TODO: Send the request

        return profile;
    }

    /**
     * Build a list of selectors for the current user.
     * 
     * @return
     * @throws GatewayException
     */
    public List<Selector> getSelectors(GatewayContext context) throws GatewayException {
        SelectorList selectors = sendRequest(context, new ListSelectorsRequest(this), SelectorList.class);
        return selectors.toList();
    }

    /**
     * Activate a selector. This operation retrieve "the pulz" associated with this selector and the current member context (space & time).
     * 
     * @param context
     * @param selectorId
     * @return
     * @throws GatewayException
     */
    public Pulz activateSelector(GatewayContext context, Long selectorId) throws GatewayException {
        return sendRequest(context, new ActivateSelectorRequest(this, selectorId), Pulz.class);
    }

    public void deleteSelector(GatewayContext context, Long selectorId) throws GatewayException {
        sendRequest(context, new DeleteSelectorRequest(this, selectorId));
    }

    public void updatePresence(GatewayContext context, Presence presence) throws GatewayException {
        sendRequest(context, new UpdatePresenceRequest(this, presence));
    }

}
