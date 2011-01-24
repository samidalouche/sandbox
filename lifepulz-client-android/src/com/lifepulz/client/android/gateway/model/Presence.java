package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;

/**
 * Wrap a presence object.
 * 
 * { "duration": 0, "location": { "longitude": -74.0, "latitude": 35.0,
 * "country": "CAD", "city": "Montreal" }, "interest": { "subject": ["hockey",
 * "sports", "canadiens", "playoffs"], "mood": ["fun", "happy"], "action":
 * ["talk", "discuss"] }, "timeframe": 0, "security": { "minKarma": 1 },
 * "language": "english", "tagline":
 * "Who wants to meet me and talk about our Canadiens?", "profile": "jgrenon" }
 * 
 * @author joelgrenon
 * 
 */
public class Presence extends JsonBackedModel {

    public Presence(HttpEntity body) {
        super(body);
    }

    public Presence() {
    }

    public Integer getDuration() {
        return getInteger("duration");
    }

    public void setDuration(Integer duration) {
        setInteger("duration", duration);
    }

    public Location getLocation() {
        return getObject("location", Location.class);
    }
    public void setLocation(Location location)  {
        setObject("location", location);
    }

    public Interest getInterest() {
        return getObject("interest", Interest.class);
    }
    
    public void setInterest(Interest interest)  {
        setObject("interest", interest);
    }

    public Integer getTimeframe() {
        return getInteger("timeframe");
    }
    
    public void setTimeframe(int timeframe) {
        setInteger("timeframe", timeframe);
    }

    public SecurityInfo getSecurityInfo() {
        return getObject("security", SecurityInfo.class);
    }
    
    public void setSecurityInfo(SecurityInfo info)  {
        setObject("security", info);
    }

    public String getLanguage() {
        return getString("language");
    }
    
    public void setLanguage(String language)    {
        setString("language", language);
    }

    public String getTagLine() {
        return getString("tagline");
    }
    
    public void setTagline(String tagline)  {
        setString("tagline", tagline);
    }

    public String getProfileAlias() {
        return getString("alias");
    }
    
    public void setProfileAlias(String alias)   {
        setString("alias", alias);
    }

    public String getStatus()   {
        return getString("status");
    }
    
    public void setStatus(String status)    {
        setString("status", status);
    }
}
