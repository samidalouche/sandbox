package com.lifepulz.client.android.gateway.model;

import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Extract a list of model objects from a JSON array
 * 
 * @author joelgrenon
 *
 * @param <T>
 */
public class JsonBackedList<T> implements ModelObject  {
	private List<T> list = new LinkedList<T>();
	private JSONArray array;
	private boolean converted;
	private Class<T> elementClass;
	
	public JsonBackedList(Class<T> returnClass, HttpEntity body)	{
		this.elementClass = returnClass;
		
		//We support empty body... until we can't anymore
		if(body != null)	{
			
			// Make sure the 
			if(body.getContentType() != null)	{
				
				// If we have json in the type, we're ok
				if(body.getContentType().getValue().contains("json"))	{
					try {
						CharBuffer buffer = CharBuffer.allocate((int)body.getContentLength());
						InputStreamReader reader = new InputStreamReader( body.getContent() );
						reader.read(buffer);
						this.array = new JSONArray(buffer.toString());
						converted = false;
					} catch (Exception e) {
						Logger.getLogger(JsonBackedModel.class.getName()).warning("Invalid request response received. Problem reading json content");
						array = null;
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
	JsonBackedList(JSONArray array)	{
		this.array = array;
	}
	
	public List<T> toList() {
		if(!converted)
			convert();
		return this.list;
	}

	/**
	 * Create all contained model objects. 
	 */
	private void convert() {
		try {
			Constructor<T> cstor = elementClass.getConstructor(JSONObject.class);

			// Make sure no objects are in the list
			list.clear();
			
			for(int i=0; i < this.array.length(); i++)	{
				list.add(cstor.newInstance(array.getJSONObject(i)));
			}
			
			converted = true;
		} catch (Exception e) {
			Logger.getLogger(JsonBackedList.class.getName()).severe(e.getMessage());
		}
	}

}
