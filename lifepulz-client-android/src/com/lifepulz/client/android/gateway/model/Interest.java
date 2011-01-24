package com.lifepulz.client.android.gateway.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;

/**
 * Wrap an interest definition
 * 
 * "interest": { "subject": ["hockey", "sports", "canadiens", "playoffs"],
 * "mood": ["fun", "happy"], "action": ["talk", "discuss"] },
 * 
 * @author joelgrenon
 * 
 */
public class Interest extends JsonBackedModel {

    public Interest(HttpEntity body) {
        super(body);
    }

    public Interest() {
    }

    public void setSubject(String subjectString) {
        List<String> tags = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(subjectString, ", ");
        while(st.hasMoreTokens())       {
            tags.add(st.nextToken());
        }
        setList("subject", tags);
    }

    public void setAction(String tagString) {
        List<String> tags = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(tagString, ", ");
        while(st.hasMoreTokens())       {
            tags.add(st.nextToken());
        }
        setList("action", tags);
    }
  
    public void setMood(String tagString) {
        List<String> tags = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(tagString, ", ");
        while(st.hasMoreTokens())       {
            tags.add(st.nextToken());
        }
        setList("mood", tags);
    }
}
