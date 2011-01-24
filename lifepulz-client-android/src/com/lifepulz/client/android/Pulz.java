package com.lifepulz.client.android;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.location.Location;

public class Pulz {
    private Date date;
    private String details;
    private Location location;
    
    public Pulz(Date date, String details, Location location) {
        super();
        this.date = date;
        this.details = details;
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    public String getDetails() {
        return details;
    }
    public Location getLocation() {
        return location;
    }
 
    @Override
    public String toString() {
      SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
      String dateString = sdf.format(date);
      return dateString + " : "  + " " + details;
    }
    
}
