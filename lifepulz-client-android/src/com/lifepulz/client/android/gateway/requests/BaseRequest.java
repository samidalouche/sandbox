package com.lifepulz.client.android.gateway.requests;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import com.lifepulz.client.android.gateway.Gateway;
import com.lifepulz.client.android.gateway.Request;
import com.lifepulz.client.android.gateway.model.ModelObject;
import com.lifepulz.client.android.gateway.model.Presence;

/**
 * Internal representation of a Gateway Request.
 * 
 * @author joelgrenon
 * 
 */
public abstract class BaseRequest implements Request {

    private final String uri;
    private final Gateway gateway;
    private final String method;
    private ModelObject content;

    private static final Map<String, Class<? extends HttpUriRequest>> requestClasses = new HashMap<String, Class<? extends HttpUriRequest>>();

    // Intialise our internal method classes map
    static {
        requestClasses.put("get", HttpGet.class);
        requestClasses.put("post", HttpPost.class);
        requestClasses.put("put", HttpPut.class);
        requestClasses.put("delete", HttpDelete.class);
    }

    public BaseRequest(final Gateway gateway, final String method,
            final String uri, final Object... params) {
        this.gateway = gateway;
        this.method = method;
        this.uri = String.format(uri, params);
    }

    Gateway getGateway() {
        return gateway;
    }

    /**
     * Build the internal http request.
     * 
     * @return
     * @throws Exception
     */
    public HttpUriRequest buildHttpRequest() throws Exception {
        HttpUriRequest request = null;

        StringBuilder uri = new StringBuilder();
        uri.append(getGateway().getBaseUrl()).append(getRequestUri());

        // Use internal service to generate the right HTTP request
        request = createNewHttpRequest(uri.toString());

        //If we have a body, update the request accordingly
        if(this.content != null && request instanceof HttpEntityEnclosingRequestBase)    {
            
            HttpEntityEnclosingRequestBase baseReq = (HttpEntityEnclosingRequestBase)request;
            baseReq.setEntity(new StringEntity(this.content.toString()));
            
        }
        
        return request;
    }

    protected String getRequestUri() {
        return uri;
    }

    protected HttpUriRequest createNewHttpRequest(final String uri)
            throws Exception {
        HttpUriRequest request = null;

        Class<? extends HttpUriRequest> clazz = requestClasses.get(method
                .toLowerCase());
        if (clazz != null) {

            Constructor<? extends HttpUriRequest> cstor = clazz
                    .getConstructor(String.class);
            request = cstor.newInstance(uri);
        }

        return request;
    }

    protected void setContent(Presence presence) {
        this.content = presence;
    }

}
