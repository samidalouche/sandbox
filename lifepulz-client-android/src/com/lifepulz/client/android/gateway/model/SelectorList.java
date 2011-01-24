package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;


public class SelectorList extends JsonBackedList<Selector> {

	public SelectorList(HttpEntity body) {
		super(Selector.class, body);
	}

}
