package com.lifepulz.client.android.gateway.model;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Provide helper methods to create model objects backed by a JSON object.
 * 
 * @author joelgrenon
 * 
 */
public abstract class JsonBackedModel implements ModelObject {

    private JSONObject content;

    public JsonBackedModel() {
        this.content = new JSONObject();
    }

    public JsonBackedModel(HttpEntity body) {

        // We support empty body... until we can't anymore
        if (body != null) {

            // Make sure the
            if (body.getContentType() != null) {

                // If we have json in the type, we're ok
                if (body.getContentType().getValue().contains("json")) {
                    InputStream is = null;
                    try {
                        
                        is = body.getContent();
                        StringBuilder jsonString = new StringBuilder();
                        byte[] buf = new byte[2048];
                        while (is.read(buf) != -1) {
                            jsonString.append(new String(buf, "UTF-8"));
                        }
                        
                        this.content = new JSONObject(jsonString.toString());
                        
                        // Indicate that we don't need the body anymore
                        body.consumeContent();
                    } catch (Exception e) {
                        Log.e(JsonBackedModel.class.getName(), "Invalid request response received. Problem reading json content");
                        content = null;
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                Log.w(JsonBackedModel.class.getName(), "Error closing the request body stream... we continue anyway");
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Internal constructor used to create JSON wrapper
     * 
     * @param content
     */
    JsonBackedModel(JSONObject content) {
        this.content = content;
    }

    protected String getString(String name) {
        String value = null;

        try {
            value = this.content.getString(name);
        } catch (JSONException e) {
            // Just return null
        }

        return value;
    }

    protected void setString(String name, String value) {
        try {
            this.content.put(name, value);
        } catch (JSONException e) {
        }
    }

    protected Integer getInteger(String name) {
        Integer value = null;

        try {
            value = this.content.getInt(name);
        } catch (JSONException e) {
            // Just return null
        }

        return value;
    }

    protected void setInteger(String name, Integer value) {
        try {
            this.content.put(name, value);
        } catch (JSONException e) {
        }
    }

    protected Double getDouble(String name) {
        Double value = null;

        try {
            value = this.content.getDouble(name);
        } catch (JSONException e) {
            // Just return null
        }

        return value;
    }

    protected void setDouble(String name, Double value) {
        try {
            this.content.put(name, value);
        } catch (JSONException e) {
        }
    }

    protected Date getDate(String name) {
        Date value = null;

        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            value = df.parse(this.content.getString(name));
        } catch (Exception e) {
            // Just return null
        }

        return value;
    }

    protected void setDate(String name, Date value) {
        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            this.content.put(name, df.format(value));
        } catch (Exception e) {
        }
    }

    protected <T extends JsonBackedModel> T getObject(String name, Class<T> returnClass) {
        T obj = null;

        try {
            Constructor<T> cstor = returnClass.getConstructor(JSONObject.class);
            obj = cstor.newInstance(content.getJSONObject(name));
        } catch (Exception e) {
            // Just return null
        }

        return obj;
    }

    protected void setObject(String name, JsonBackedModel value) {
        try {
            this.content.put(name, value.content);
        } catch (JSONException e) {
        }
    }
    
    protected void setList(String name, List<String> list) {
        try {
            this.content.put(name, new JSONArray(list));
        } catch (JSONException e) {
        }
    }

    protected List<String> getList(String name) {
        List<String> list = Collections.emptyList();
        
        try {
            JSONArray array = this.content.getJSONArray(name);
            list = new ArrayList<String>(array.length());
            for(int i=0; i< array.length(); i++)    {
                list.add(array.getString(i));
            }
        } catch (JSONException e) {
        }
        
        return list;
    }
    
    public String toString()    {
        if(content != null)
            return content.toString();
        else
            return "";
    }
}
